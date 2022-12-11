let header = "";
let footer = "";
let site_name = "SiteName";
let allText = [];
let rightSideTexts = [];
let leftSideTexts = [];

// ---------------------------------------- ТЕКСТ ---------------------------------------------

document.getElementById("text").addEventListener("click", (e) => {
    var text = document.getElementById("added_text").value;

    if(document.getElementById("sentence").checked) {
        text += ". ";
    }

    if(document.getElementById("heading").checked) {
        text = "<h2>" + text + "</h2>";
    }

    if(document.getElementById("new").checked) {
        text = "<br>    " + text;
    }

    allText.push(text);

    if(document.getElementById("rightTextPos").checked) {
        rightSideTexts.push(text);
        document.getElementById("right_block").innerHTML += text;
    } else if(document.getElementById("leftTextPos").checked) {
        leftSideTexts.push(text);
        document.getElementById("left_block").innerHTML += text;
    }
});

document.getElementById("editLastText").addEventListener("click", (e) => {
    var lastText = allText[allText.length - 1];

    allText.pop();

    if(leftSideTexts[leftSideTexts.length - 1] === lastText) {
        leftSideTexts.pop();

        document.getElementById("left_block").innerHTML = "";

        for(let i = 0; i < leftSideTexts.length; i++) {
            document.getElementById("left_block").innerHTML += leftSideTexts[i];
        }

    } else if(rightSideTexts[rightSideTexts.length - 1] === lastText) {
        rightSideTexts.pop();

        document.getElementById("right_block").innerHTML = "";

        for(let i = 0; i < rightSideTexts.length; i++) {
            document.getElementById("right_block").innerHTML += rightSideTexts[i];
        }
    }

    document.getElementById("added_text").value = lastText;
});

// ---------------------------------------- ШАПКИ ---------------------------------------------

document.getElementById("header_1").addEventListener("click", (e) => {
    site_name = document.getElementById("header_text").value;

    document.getElementById("header_block").innerHTML = `<header class="p-3 bg-dark text-white">
            <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <li>` + site_name + `</li>
            </ul>
            </div>
            </div>
            </header>`;
    
    header = "1";
});

document.getElementById("header_2").addEventListener("click", (e) => {
    site_name = document.getElementById("header_text").value;

    document.getElementById("header_block").innerHTML = `<header class="d-flex justify-content-center py-3">
    <ul class="nav nav-pills">
    <li class="nav-item"><a class="nav-link" aria-current="page">Кнопка</a></li>
    <li class="nav-item"><a class="nav-link">Кнопка))0)</a></li>
    <li class="nav-item"><a class="nav-link">Кнопка еще</a></li>
    <li class="nav-item"><a class="nav-link">Кнопка оаоаоаоа</a></li>
    <li class="nav-item"><a class="nav-link">Knopka</a></li>
    </ul>
    </header>`;
    header = "2";
});

// ---------------------------------------- ФУТЕРЫ ---------------------------------------------

document.getElementById("footer_1").addEventListener("click", (e) => {
    site_name = document.getElementById("header_text").value;

    document.getElementById("footer_block").innerHTML = `<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-muted">© 2022 ` + site_name + `</p>
    </footer>`;
    footer = "1";
});

document.getElementById("footer_2").addEventListener("click", (e) => {
    site_name = document.getElementById("header_text").value;

    document.getElementById("footer_block").innerHTML = `<div class="container">
    <footer class="py-3 my-4">
    <p class="text-center text-muted">© 2022 ` + site_name + `</p>
    </footer>
    </div>`;
    footer = "2";
});




document.getElementById("done").addEventListener("click", (e) => {

    site_name = document.getElementById("header_text").value;
    let left_content = document.getElementById("left_block").innerHTML;
    let right_content = document.getElementById("right_block").innerHTML;


    var json = JSON.stringify({
        "header" : header,
        "footer" : footer,
        "siteName" : site_name,
        "leftContent" : left_content,
        "rightContent" : right_content
    });

    var request = new XMLHttpRequest();
    request.open("POST", "/generate", true);
    request.setRequestHeader("Content-Type", "application/json");

    request.send(json);

});

document.getElementById("download").addEventListener("click", (e) => {

    site_name = document.getElementById("header_text").value;
    window.location.href = "http://localhost:8080/download/" + site_name;
    
});
