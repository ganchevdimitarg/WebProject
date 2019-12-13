const URLS = {
    items: 'api/animal',
};

const toString = ({breed, name, age, disease, medicines, medicine, description, doctor}) => {
    let columns = `
<div class="col-md-2">
    [[${breed}]]
</div>
<div class="col-md-2">
    [[${name}]]
</div>
<div class="col-md-2">
    [[${age}]]
</div>
<div class="col-md-2">
    [[${disease}]]
</div>

</div>
<th:block th:if="${doctor == null}">
    <div class="col-md-2">
    </div>
</th:block>
<th:block th:unless="${doctor == null}">
    <div class="col-md-2">
        [[${doctor.name}]]
    </div>
</th:block>
<div class="col-md-2">
    <form action="/user/pet/delete/${name}" method="post">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
`
    return `<div class="row">${columns}</div>`
};

fetch(URLS.animal)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
            const itemString = toString(item);
            result += itemString;
        });
        
        $('#animal').append(result);
    });

$('#animal').on('submit', '.delete-schedule', function (ev) {
    const url = $(this).attr('action');

    fetch(url, { method: 'post' })
        .then(data => {
        console.log(data)
        window.location = '/pet/delete/{name}';
});

    ev.preventDefault();
    return false;
});
