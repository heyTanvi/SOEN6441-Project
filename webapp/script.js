const countryMap = {
  AD: 'Andorra',
  AL: 'Albania',
  AM: 'American Samoa',
  AF: 'Afghanistan',
  BB: 'Barbados',
  BY: 'Belarus',
  BE: 'Belgium',
  BZ: 'Belize',
};

const countryList = [];
function createDropdownList(country, key) {
  liEle = document.createElement('li');
  aEle = document.createElement('a');
  aEle.className = 'dropdown-item country-dropdown';
  aEle.setAttribute('id', key);
  aEle.title = country;
  aEle.innerHTML = country;
  liEle.appendChild(aEle);
  countryList.push(aEle);
  return liEle;
}

const countryEle = document.getElementById('country-dropdown');
Object.keys(countryMap).forEach((key) => {
  countryEle.prepend(createDropdownList(countryMap[key], key));
});

for (let i = 0; i < countryList.length; i++) {
  countryList[i].addEventListener('click', () =>
    getCountryRelatedInfo(countryList[i])
  );
}

function getCountryRelatedInfo(countryE) {
  let COUNTRY_CASES = `http://localhost:8080/SOEN6441-Project/register?filter=country&code=${countryE.id}`;
  document.getElementById(
    'tag'
  ).innerHTML = `${countryE.innerHTML} Covid Cases`;
  fetch(COUNTRY_CASES)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      document.getElementById('new_confirmed').innerHTML = data.newConfirmed;
      document.getElementById('total_confirmed').innerHTML =
        data.totalConfirmed;
      document.getElementById('new_death').innerHTML = data.newDeaths;
      document.getElementById('total_death').innerHTML = data.totalDeaths;
      document.getElementById('new_recovered').innerHTML = data.newRecovered;
      document.getElementById('total_recovered').innerHTML =
        data.totalRecovered;
    })
    .catch((err) => console.error(err));
}
function getGlobal() {
  let GLOBAL_CASES =
    'http://localhost:8080/SOEN6441-Project/register?filter=global';
  document.getElementById('main-div').style.display = 'block';
  document.getElementById('myChart').style.display = 'none';
  document.getElementById('animatediv').style.display = 'block';
  document.getElementById('tag').innerHTML = 'Global Covid-19 Cases';
  fetch(GLOBAL_CASES)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      document.getElementById('new_confirmed').innerHTML = data.newConfirmed;
      document.getElementById('total_confirmed').innerHTML =
        data.totalConfirmed;
      document.getElementById('new_death').innerHTML = data.newDeaths;
      document.getElementById('total_death').innerHTML = data.totalDeaths;
      document.getElementById('new_recovered').innerHTML = data.newRecovered;
      document.getElementById('total_recovered').innerHTML =
        data.totalRecovered;
    })
    .catch((err) => console.error(err));
}

getGlobal();

document.getElementById('global-cases').addEventListener('click', getGlobal);

document.getElementById('analysis').addEventListener('click', drawChart);
function drawChart() {
  let countryCount = {};
  let COUNTRY_BY_CODES =
    'http://localhost:8080/SOEN6441-Project/register?filter=analysis&codes=AD,AL,AF,AM,BB,BY,BE,BZ';

  fetch(COUNTRY_BY_CODES)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      let len = data.length > 50 ? 50 : data.length;

      for (let i = 0; i < len; i++) {
        const cc = data[i].countryCode;
        const cv = data[i].totalConfirmed;
        if (countryCount[cc]) {
          countryCount[cc] = countryCount[cc] + (cv % 100);
        } else {
          countryCount[cc] = cv % 100;
        }
      }
      document.getElementById('main-div').style.display = 'none';
      document.getElementById('myChart').style.display = 'block';
      document.getElementById('animatediv').style.display = 'none';
      var xValues = Object.keys(countryCount);
      var yValues = Object.values(countryCount);
      var barColors = [
        'red',
        'green',
        'blue',
        'orange',
        'brown',
        'yellow',
        'pink',
      ];

      new Chart('myChart', {
        type: 'bar',
        data: {
          labels: xValues,
          datasets: [
            {
              backgroundColor: barColors,
              data: yValues,
            },
          ],
        },
        options: {
          legend: { display: false },
          title: {
            display: true,
            text: 'World Total Confirmed Cases',
          },
        },
      });
    });
}
