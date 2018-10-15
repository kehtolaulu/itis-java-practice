'use strict';

const getNumber = () => {
    $.ajax({
        url: '/number.json',
        type: 'GET',
        success: (msg) => {
            $('#number-field').val(msg.number);
        }
    });
}