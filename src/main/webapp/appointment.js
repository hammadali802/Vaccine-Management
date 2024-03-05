function createTimeOptions() {
    const timeSelect = document.getElementById('time');
    timeSelect.innerHTML = ''; // Zurücksetzen der vorherigen Optionen

    // Zeitoptionen von 08:00 bis 18:00 mit einer Differenz von 20 Minuten
    for (let hour = 8; hour < 18; hour++) {
        for (let minute = 0; minute < 60; minute += 20) {
            const timeString = `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}`;
            const option = document.createElement('option');
            option.textContent = timeString;
            timeSelect.appendChild(option);
        }
    }

    timeSelect.toString();
}


// Beim Laden der Seite Zeitoptionen erstellen
window.onload = function() {
    createTimeOptions();
};
function updateVaccineOptions(){

    const location = document.getElementById('location').value;
    const vaccineSelect = document.getElementById('vaccine');

    // Clear previous options
    vaccineSelect.innerHTML = '<option value="">Bitte wählen</option>';

    // Populate options based on selected location
    if (location === 'location1') {
        // Location 1 options
        const optionA = document.createElement('option');
        optionA.textContent = 'Impfstoff A';
        optionA.value = 'A';
        vaccineSelect.appendChild(optionA);

        const optionB = document.createElement('option');
        optionB.textContent = 'Impfstoff B';
        optionB.value = 'B';
        vaccineSelect.appendChild(optionB);
    } else if (location === 'location2') {
        // Location 2 options
        const optionA = document.createElement('option');
        optionA.textContent = 'Impfstoff A';
        optionA.value = 'A';
        vaccineSelect.appendChild(optionA);

        const optionC = document.createElement('option');
        optionC.textContent = 'Impfstoff C';
        optionC.value = 'C';
        vaccineSelect.appendChild(optionC);

        const optionD = document.createElement('option');
        optionD.textContent = 'Impfstoff D';
        optionD.value = 'D';
        vaccineSelect.appendChild(optionD);
    }
    JSON.stringify(location);
    JSON.stringify(vaccineSelect);
}

const dateInput = document.getElementById("appointmentdate");

// Set the minimum date to today
dateInput.min = new Date().toISOString().split('T')[0];

console.log('Standort:', typeof location);
console.log('Impfstoff:', typeof vaccine);
console.log('Zeit:', typeof time);
console.log('date INput ', typeof dateInput);

 const bookingType = document.getElementById('bookingType');

//
// // Function to toggle display of relative information based on booking type
 bookingType.addEventListener('change', function() {
  if (bookingType.value === 'relative') {
   relativeInfo.style.display = 'block';
  } else {
    relativeInfo.style.display = 'none';
  }
 });