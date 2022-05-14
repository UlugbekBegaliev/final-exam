'use strict';

function showModalWin() {

    var darkLayer = document.createElement('div');
    darkLayer.id = 'shadow';
    document.body.appendChild(darkLayer);

    var modalWin = document.getElementById('modal_window');
    modalWin.hidden = false;
    modalWin.style.display = 'block';

    darkLayer.onclick = function () {
        darkLayer.parentNode.removeChild(darkLayer);
        modalWin.style.display = 'none';
        return false;
    };
}

const saveButton = document.getElementById("add-message");
saveButton.addEventListener("click", function () {
    const reviewForm = document.getElementById("review-form");
    let data = new FormData(reviewForm);
    let place_id = data.get("place_id");

    fetch("/createReview", {
        method: 'POST',
        body: data
    }).then(r => r.json()).then(data => {
        window.location.href = "/places/" + place_id
    });
});

function search() {
    let value = document.getElementById("search-input").value;
    if (value.length === 0) {
        return;
    } else {
        window.location.href = '/search/' + value;
    }

};
