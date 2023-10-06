function rebuild_table(data) {
    let table = document.getElementById('hotelsFreeTable');
    table.innerHTML = '';

    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
                    <td>${data[i].hotelName}</td>
                    <td>${data[i].stars}</td>
                    <td>${data[i].city}</td>
                    <td>${data[i].cntRooms}</td>
                    <td>
                    <input type="button" onclick="create_booking(${data[i].id})" value="Забронировать">
                    </td>
                </tr>`;
        table.innerHTML += row;
    }
}

function send_filters() {
    $.ajax({
        url: '/api/hotels/free',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            city: $("#city").val(),
            dateFrom: $("#dateFrom").val(),
            dateTo: $("#dateTo").val(),
            stars: $("#stars").val()
        }),
        success: function (data) {
            rebuild_table(data);
        },
        error: function () {
            let error = document.getElementById('filterError');
            error.innerHTML = `<h4>Все поля должны быть обязательно заполнены</h4>`
        },
    });
}