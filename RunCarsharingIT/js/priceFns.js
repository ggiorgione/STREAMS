var priceType = {
    HORIZONTAL : "horizontal",
    VERTICAL : "vertical",
    BASE: "base"
};

var bound = {
    LOWER : "lower",
    UPPER : "upper"
};


var timeBounds= [{time: [3.0, 6.0], lowerBound: 6.1, upperBound: 9.0},
                {time: [6.0, 9.0], lowerBound: 6.1, upperBound: 9.0},
                {time: [9.0, 12.0], lowerBound: 9.1, upperBound: 12.0},
                {time: [12.0, 15.0], lowerBound: 12.1, upperBound: 15.0},
                {time: [15.0, 18.0], lowerBound: 15.1, upperBound: 18.0},
                {time: [18.0, 21.0], lowerBound: 18.1, upperBound: 21.0},
                {time: [21.0, 24.0], lowerBound: 21.1, upperBound: 24.0}
                ];

var defTimeBounds = {lowerBound: 0.1, upperBound: 3.0};

var scaleToMatchCar = 1.0;
var distCost = 0.0;



function computePrice(rentalInfo, prices){
    return prices.getPricing() === priceType.HORIZONTAL
          ? horizontalPrice(rentalInfo, prices)
          : basePrice(rentalInfo, prices)
}

function horizontalPrice(rentalInfo, prices){

    var ST = rentalInfo.getStartTime();
    var ET = rentalInfo.getEndTime();
    var priceHighRateHorizontal = prices.getPriceHighRateHorizontal();
    var priceLowRateHorizontal = prices.getPriceLowRateHorizontal();

    //Start and end time from seconds to hours
    var ST_Hour = ST / 3600;
    var ET_Hour = ET / 3600;

    // StartTime slot upper bound
    var ST_UB = getTimeSlotBound(ST_Hour, bound.UPPER);

    // Time until next slot in hour
    var LB = ST_UB - ST_Hour;

    //Start time slot cost
    var ST_SlotCost = getPriceRate(ST_Hour, prices);

    // Cost until the end of the first time slot
    var Cb = LB * 60 * ST_SlotCost;

    // EndTime slot Lower bound
    var ET_LB = getTimeSlotBound(ET_Hour, bound.LOWER);

    // Time until next slot in hour
    var UB = ET_Hour - ET_LB;

    //End time slot cost
    var ET_SlotCost = getPriceRate(ET_Hour, prices);

    // Cost until the end of the first time slot
    var Ce = UB * 60 * ET_SlotCost;

    var TI = Math.abs(ET_LB - ST_UB);

    //Ci
    var Ci = 0.0;
    if(parseInt(TI/3) % 2 === 0) {
        Ci = (parseInt(TI/2)*60)*priceHighRateHorizontal+(parseInt(TI/2)*60)*priceLowRateHorizontal;
    }else if(parseInt(TI/3) === 1) {
        if(ST_SlotCost === priceHighRateHorizontal) {
            Ci = TI*60*priceLowRateHorizontal;
        }else {
            Ci = TI*60*priceLowRateHorizontal;
        }
    }else if(parseInt(TI) === 0){
        Ci = 0;
    }else {
        Ci = (parseInt((TI-1)/2)*60)*priceHighRateHorizontal+(parseInt((TI-1)/2)*60)*priceLowRateHorizontal+(1*60*ST_SlotCost);
    }

    var costSum = Cb + Ce + Ci;
    return parseFloat(costSum.toFixed(2))


}



function basePrice(rentalInfo, prices){
    var ST = rentalInfo.getStartTime();
    var ET = rentalInfo.getEndTime();
    var priceBaseDriving = prices.getPriceBaseDriving();
    var priceBaseStop = prices.getPriceBaseStop();


    var rentalTime = ET - ST;
    var inVehicleTime = rentalInfo.getInVehicleTime();
    var distance = rentalInfo.getDistance();

    var evalTime = (priceBaseDriving * (inVehicleTime / 60)) + (((rentalTime - inVehicleTime) / 60.0) * priceBaseStop);

    var evalDist = distCost * (distance / 1000);
    var costNum = scaleToMatchCar * (evalTime + evalDist);
    return parseFloat(costNum.toFixed(2))
}



function getPriceRate(time, prices){
    return ((time > 18 && time <= 21) || (time > 12 && time <= 15) || (time > 6 && time <= 9)) ? prices.getPriceHighRateHorizontal() : prices.getPriceLowRateHorizontal();
}




function getTimeSlotBound(time, boundType){
    var timeSlotBound = timeBounds.filter(function (tb) {return time > tb.time[0] && time <= tb.time[1]});
    if(timeSlotBound.length === 1) {
        return boundType === bound.LOWER ? timeSlotBound[0].lowerBound : timeSlotBound[0].upperBound;
    }else if(timeSlotBound.length > 1){
        throw new Error(timeSlotBound);
    }else
        return boundType === bound.LOWER ? defTimeBounds.lowerBound : defTimeBounds.upperBound;
}