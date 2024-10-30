document.getElementById('country-select').addEventListener('change', function() {
    const countryId = this.value;
    const citySelect = document.getElementById('city-select');

    if (countryId != 0) {
        fetch(`/cities?countryId=${countryId}`)
            .then(response => response.json())
            .then(data => {
                citySelect.innerHTML = '<option value="">Город</option>'; // Очистить список городов
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
document.getElementById('form_11949').addEventListener('submit', function(e) {
    let isValid = true;
    let errorMessage = '';

    // Проверка выбора страны
    const country = document.getElementById('country-select').value;
    if (!country) {
        isValid = false;
        errorMessage += 'Пожалуйста, выберите страну\n';
    }

    // Проверка выбора города
    const city = document.getElementById('city-select').value;
    if (!city) {
        isValid = false;
        errorMessage += 'Пожалуйста, выберите город\n';
    }

    // Проверка валюты покупки
    const currencyBuy = document.getElementById('currency-buy-select').value;
    if (!currencyBuy) {
        isValid = false;
        errorMessage += 'Пожалуйста, выберите валюту для покупки\n';
    }

    // Проверка валюты продажи
    const currencySell = document.getElementById('currency-sell-select').value;
    if (!currencySell) {
        isValid = false;
        errorMessage += 'Пожалуйста, выберите валюту для продажи\n';
    }

    if (!isValid) {
        e.preventDefault(); // Останавливаем отправку формы
        alert(errorMessage); // Показываем сообщение об ошибке
    }
});