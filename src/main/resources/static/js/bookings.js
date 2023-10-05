function create_booking(hotelId) {
    $.ajax({
        url: '/api/bookings',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            hotelId: hotelId,
            dateFrom: document.getElementById('dateFrom').value,
            dateTo: document.getElementById('dateTo').value
        }),
        success: function (data) {
            alert(data)
            send_filters()
        },
        error: function () {
            let error = document.getElementById('filterError');
            error.innerHTML = `<h4>Ошибка бронирования</h4>`
        },
    });
}