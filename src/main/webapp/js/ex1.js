
var elements = document.getElementsByTagName("div");
elements[0].style.backgroundColor = "green";
elements[1].style.backgroundColor = "blue";
elements[2].style.backgroundColor = "red";
var changeBack = false;

function changeColor() {
    if (!changeBack) {
    document.getElementById("div1").style.backgroundColor = "yellow";
    document.getElementById("div2").style.backgroundColor = "purple";
    document.getElementById("div3").style.backgroundColor = "black";
    changeBack = true;
} else {
    document.getElementById("div1").style.backgroundColor = "green";
    document.getElementById("div2").style.backgroundColor = "blue";
    document.getElementById("div3").style.backgroundColor = "red";
    changeBack = false;
}
}

document.getElementById("btn1").addEventListener("click", changeColor, false);
