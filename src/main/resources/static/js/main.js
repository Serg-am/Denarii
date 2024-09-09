document.getElementById('country-select').addEventListener('change', function() {
    const countryId = this.value;
    const citySelect = document.getElementById('city-select');

    if (countryId != 0) {
        fetch(`/cities?countryId=${countryId}`)
            .then(response => response.json())
            .then(data => {
                citySelect.innerHTML = '<option value="0">Город</option>'; // Очистить список городов
                data.forEach(city => {
                    const option = document.createElement('option');
                    option.value = city.id;
                    option.textContent = city.name;
                    citySelect.appendChild(option);
                });
            })
            .catch(error => console.error('Ошибка загрузки городов:', error));
    } else {
        citySelect.innerHTML = '<option value="0">Город</option>'; // Очистить список, если страна не выбрана
    }
});
