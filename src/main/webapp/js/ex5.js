
const display = document.getElementById("display");
const buttons = document.getElementById("buttons");
const calculate = document.getElementById("calculate");
var hasCalculated = false;


function getInput() {
    var input = document.getElementById(event.target.id);
    var test = input.innerHTML;
    if (!isNaN(test) || input.id !== "buttons") {
    if (hasCalculated === true) {
        display.innerHTML = "";
        hasCalculated = false;
    }
    display.innerHTML += input.innerHTML;
}
}

function doCalculation() {
    event.stopPropagation();
    var equation = display.innerHTML;
    try {
    display.innerHTML = eval(equation);
    hasCalculated = true;
    } catch (Exception) {
    alert("Invalid input: could not calculate.");
    display.innerHTML = "";
    }
}


buttons.addEventListener("click", getInput, false);
calculate.addEventListener("click", doCalculation, false);

