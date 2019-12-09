const URLS = {
    items: 'api/medicine',
};

const toString = ({imageUrl, name, description}) => {
    let columns = `
<div class="col-md-6">
    <img th:src="${imageUrl}" alt="doctor-image">
</div>
<div class="col-md-6">
    <h4>Name: ${name}</h4>
    <h4>Description: ${description}</h4>
</div>

`
    return `<div class="row">${columns}</div>`
};

fetch(URLS.items)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
            const itemString = toString(item);
            result += itemString;
        });
        console.log(result);

        $('.medicines').html(result);
    });

$('.medicines').on('submit', '.delete-schedule', function (ev) {
    const url = $(this).attr('action');

    fetch(url, { method: 'post' })
        .then(data => {
        console.log(data)
        window.location = '/schedule/delete/{date}';
});

    ev.preventDefault();
    return false;
});
