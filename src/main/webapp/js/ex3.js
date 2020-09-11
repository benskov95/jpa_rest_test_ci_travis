
const div = document.getElementById("iwantnames");
const names = ["Sven", "Bobby", "Marianne", "Marius", "Olivia"];

function htmlNames(array) {
    var res = "<ul>";
    var withTags = array.map(name => "<li>" + name + "</li>");
    res += withTags.join("");
    res += "</ul>";
    return res;
}

    div.innerHTML = htmlNames(names);

function addNewName() {
    event.preventDefault();
    var input = document.getElementById("name");
    if (input.value.length < 1) {
        alert("Type something in the text box first.");
    } else {
    names.push(input.value);
    div.innerHTML = htmlNames(names);
}
}


function determineAction() {
    event.preventDefault();
    var input = document.getElementById(event.target.id);
    if (input.id === "removeFirst") {
        names.shift(input.value);
    } else if (input.id === "removeLast") {
        names.pop(input.value);
    } else if (input.id === "add") {
        addNewName();
    } 
    div.innerHTML = htmlNames(names);
}

document.getElementById("form").addEventListener("click", determineAction, false);

