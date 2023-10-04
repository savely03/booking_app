function create_booking(hotelId, dateFrom, dateTo) {
    $.ajax({
        url: '/api/bookings',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            username: 'user',
            hotelId: hotelId,
            dateFrom: dateFrom,
            dateTo: dateTo
        }),
        error: function () {
            let error = document.getElementById('filterError');
            error.innerHTML = `<h4>Ошбика бронирования</h4>`
        },
    });
}