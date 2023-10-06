function rebuild_hotels_table(data) {
    let table = document.getElementById('hotelsTable');
    table.innerHTML = '';

    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
            <td>${data[i].hotelName}</td>
            <td>${data[i].city}</td>
            <td>${data[i].totalRooms}</td>
            <td>${data[i].busyRooms}</td>
            <td>${data[i].freeRooms}</td>
            <td>
                <a href="/hotels/${data[i].id}">
                    <button>Подробнее</button>
                </a>
            </td>
            <td>
                <a href="/rooms?hotelId=${data[i].id}">
                    <button>Номера</button>
                </a>
            </td>
        </tr>`;
        table.innerHTML += row;
    }
}

function hotels_all() {
    $.ajax({
        url: '/api/hotels',
        dataType: 'json',
        type: 'GET',
        data: {
            "city": $("#h_city").val(),
            "stars": $("#h_stars").val()
        },
        success: function (data) {
            rebuild_hotels_table(data);
        },
        error: function () {
            let error = document.getElementById('hotels_all_error');
            error.innerHTML = `<h4>Что-то пошло не так...</h4>`
        },
    });
}