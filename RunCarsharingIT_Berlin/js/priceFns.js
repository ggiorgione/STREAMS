var priceType = Java.type("org.matsim.contrib.carsharing.runExample.PriceType");

var scaleToMatchCar = 1.0;
var distCost = 0.0;
var minConst = 60.0;
var flexGracePeriodMin = 5.0;
var planGracePeriodMin = 20.0;

                                                                            //[Time ranges]   [Price €/h]
var timeBasePriceFn = flatten([createHourlyPrinceTimeRange(0.0, 6.0, 4.0),  //00:00 - 04:00     6
                               createHourlyPrinceTimeRange(6.0, 9.0, 8.0),  //04:00 - 07:00     6.5
                               createHourlyPrinceTimeRange(9.0, 12.0, 6.0), //07:00 - 15:00     7.0
                               createHourlyPrinceTimeRange(12.0, 15.0, 7.0), //15:00 - 20:00     6.5
                               createHourlyPrinceTimeRange(15.0, 17.0, 6.0), //20:00 - 25:00     6.0
                               createHourlyPrinceTimeRange(17.0, 19.0, 8.0), //25:00 - 30:00     5.0
                               createHourlyPrinceTimeRange(19.0, 21.0, 5.0), //25:00 - 30:00     5.0
                               createHourlyPrinceTimeRange(21.0, 30.0, 4.0), //25:00 - 30:00     5.0
                                ]);

var timeBasePriceFnMaxIdx = timeBasePriceFn.length-1;


function computePrice(rentalInfo, prices){
    return prices.getPricing() === priceType.TIME_BASE.getPriceType()
          ? timeBasePrice(rentalInfo, prices)
          : fixedPrice(rentalInfo, prices)
}

function fixedPrice(rentalInfo, prices){
    var startRentTime = rentalInfo.getStartTime();
    var endRentTime = rentalInfo.getEndTime();
    var priceBaseDriving = prices.getPriceBaseDriving();
    var priceBaseStop = prices.getPriceBaseStop();
    var enableOplyPricePolicy = prices.isEnableOplyPricePolicy();


    var rentalTime = endRentTime - startRentTime;

    var adjRentalTimeSec = enableOplyPricePolicy
        ? adjustEndRentTimeOplyPolicy(rentalTime/minConst, flexGracePeriodMin)*minConst
        : rentalTime;

    var inVehicleTime = rentalInfo.getInVehicleTime();
    var distance = rentalInfo.getDistance();

    var evalTime = (priceBaseDriving * (inVehicleTime / minConst)) + (((adjRentalTimeSec - inVehicleTime) / minConst) * priceBaseStop);

    var evalDist = distCost * (distance / 1000);
    var costNum = scaleToMatchCar * (evalTime + evalDist);
    return parseFloat(costNum.toFixed(2))

}


function timeBasePrice(rentalInfo, prices){

    var startRentTimeMin = rentalInfo.getStartTime()/minConst;
    var endRentTimeMin = rentalInfo.getEndTime()/minConst;
    var rentalTimeMin = endRentTimeMin - startRentTimeMin;
    var enableOplyPricePolicy = prices.isEnableOplyPricePolicy();

    var adjRentalTimeMin = enableOplyPricePolicy
        ? adjustEndRentTimeOplyPolicy(rentalTimeMin, flexGracePeriodMin)
        : rentalTimeMin;

    var adjUpperBoundRentalTimeMin = startRentTimeMin + adjRentalTimeMin;

    var totalPrice = 0.0;

    for(var i = Math.floor(startRentTimeMin); i < Math.floor(adjUpperBoundRentalTimeMin); i++){
        totalPrice = totalPrice + ((i<=timeBasePriceFnMaxIdx) ? timeBasePriceFn[i] : timeBasePriceFn[timeBasePriceFnMaxIdx]);
    }

    return totalPrice;

}

/**
 * Adjust endRentTime to the next "o'clock hour" if overtakes grace period
 * @param endRentTime
 * @param gracePeriod
 * @returns {*}
 */
function adjustEndRentTimeOplyPolicy(endRentTime, gracePeriod){
    var addMinute = endRentTime%minConst;
    if(addMinute > gracePeriod){
        var effectiveHours = parseInt(endRentTime/minConst);
        return (effectiveHours + 1)*minConst;
    }else
        return endRentTime;
}




function createHourlyPrinceTimeRange(startTimeHour, endTimeHour, priceHour){
    var rangeMin = (endTimeHour - startTimeHour)*minConst;
    var priceMin = parseFloat((priceHour/minConst).toFixed(2));
    return createArrayNumbers(priceMin, rangeMin)
}

/**
 * Create array of length n of a single number
 * @param number
 * @param timesNumber
 */
function createArrayNumbers(number, timesNumber){
    return Array.apply(null, Array(timesNumber)).map(Number.prototype.valueOf, number);
}


/**
 * Flat an array.
 * This is done in a linear time O(n) without recursion
 * memory complexity is O(1) or O(n) if mutable param is set to false
 * Ex.
 * var array = [[0, 1], [2, 3], [4, 5, [6, 7, [8, [9, 10]]]]];
 * flatten(array)
 * [0,1,2,3,4,5,6,7,8,9,10]
 * @param array
 * @param mutable
 * @returns {Array}
 */
function flatten(array, mutable) {
    var toString = Object.prototype.toString;
    var arrayTypeStr = '[object Array]';

    var result = [];
    var nodes = (mutable && array) || array.slice();
    var node;

    if (!array.length) {
        return result;
    }

    node = nodes.pop();

    do {
        if (toString.call(node) === arrayTypeStr) {
            nodes.push.apply(nodes, node);
        } else {
            result.push(node);
        }
    } while (nodes.length && (node = nodes.pop()) !== undefined);

    result.reverse(); // we reverse result to restore the original order
    return result;
}






