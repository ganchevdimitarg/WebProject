const URLS = {
    items: 'api/items',
};

const toString = ({dateReview, doctor, animal}) => {
    let columns = `
<div class="col-md-2">
   ${dateReview}
</div>
<div class="col-md-2">
    ${doctor}
</div>
<div class="col-md-2">
    ${animal}
</div>
<div class="col-md-2">
    <a class="btn btn-info add-treatment" href="/doctor/add-treatment/${animal}">Add Treatment</a>
</div>
<div class="col-md-2">
    <form method="post" action="/doctor/schedule/delete/${dateReview}">
        <input type="submit" value="Complete" class="btn btn-danger"/>
    </form>
</div>
`
    return `${columns}`
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

        document.getElementsByClassName('schedule-table').innerHTML = result;
        // $('.schedule-table').html(result);
    });

$('.schedule-table').on('submit', '.delete-schedule', function (ev) {
    const url = $(this).attr('action');

    fetch(url, { method: 'post' })
        .then(data => {
        console.log(data)
        window.location = '/schedule/delete/{date}';
});

    ev.preventDefault();
    return false;
});
