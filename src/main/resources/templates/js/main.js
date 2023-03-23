// HTML 문서의 요소를 가져옵니다.
const form = document.querySelector('form');
const nameInput = document.querySelector('#name');
const addressInput = document.querySelector('#address');
const latitudeInput = document.querySelector('#latitude');
const longitudeInput = document.querySelector('#longitude');
const placesTableBody = document.querySelector('#placesTableBody');

// 서버로부터 모든 장소 정보를 가져오는 함수를 작성합니다.
async function getAllPlaces() {
    const response = await fetch('/api/place');
    const places = await response.json();
    return places;
}

// 장소 정보를 테이블에 추가하는 함수를 작성합니다.
function appendPlaceToTable(place) {
    const tr = document.createElement('tr');

    const nameTd = document.createElement('td');
    nameTd.textContent = place.name;
    tr.appendChild(nameTd);

    const addressTd = document.createElement('td');
    addressTd.textContent = place.address;
    tr.appendChild(addressTd);

    const latitudeTd = document.createElement('td');
    latitudeTd.textContent = place.latitude;
    tr.appendChild(latitudeTd);

    const longitudeTd = document.createElement('td');
    longitudeTd.textContent = place.longitude;
    tr.appendChild(longitudeTd);

    const deleteTd = document.createElement('td');
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.addEventListener('click', async () => {
        await fetch(`/api/place/${place.id}`, {
            method: 'DELETE',
        });
        tr.remove();
    });
    deleteTd.appendChild(deleteButton);
    tr.appendChild(deleteTd);

    placesTableBody.appendChild(tr);
}

// 서버로부터 모든 장소 정보를 가져와서 테이블에 추가하는 함수를 호출합니다.
getAllPlaces().then((places) => {
    places.forEach((place) => {
        appendPlaceToTable(place);
    });
});

// 폼 데이터를 서버로 전송하는 이벤트 리스너를 등록합니다.
form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const data = {
        name: nameInput.value,
        address: addressInput.value,
        latitude: parseFloat(latitudeInput.value),
        longitude: parseFloat(longitudeInput.value),
    };
    const response = await fetch('/api/place', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    const place = await response.json();
    appendPlaceToTable(place);
    form.reset();
});
