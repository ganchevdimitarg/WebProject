const URLS = {
    items: 'api/medicine',
};

const toString = ({imageUrl, name, description}) => {
    let columns = `
<div class="col-md-6">
    <img src="${imageUrl}" alt="doctor-image">
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

        $('#medicines').append(result);
    });
