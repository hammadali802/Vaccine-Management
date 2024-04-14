function initializePage() {
    // Function to handle center selection from select box
    function onCenterSelected() {
        const centerSelect = document.getElementById("location");
        const selectedOption = centerSelect.options[centerSelect.selectedIndex];
        if (selectedOption.value) {
            const center = JSON.parse(selectedOption.value);
            getTimeSlots(center.id);
            document.getElementById("centerTable").style.display = "table"; // Show table when center selected
        } else {
            document.getElementById("centerTable").style.display = "none"; // Hide table when no center selected
        }
    }

    // Call the initializePage function when the DOM content is loaded
    window.addEventListener("DOMContentLoaded", function() {
        // Fetch and populate centers select box
        getCenters();
        // Show the select box initially
        document.getElementById("location").style.display = "block";
        // Hide the time slot table initially
        document.getElementById("centerTable").style.display = "none";
    });

    // Add event listener to handle center selection changes
    const centerSelect = document.getElementById("location");
    centerSelect.addEventListener("change", onCenterSelected);
}

// Call the initializePage function when the DOM content is loaded
initializePage();

function getCenters() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "centers", true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            processCenters(JSON.parse(xhr.responseText));
        } else {
            console.error("Error retrieving data:", xhr.statusText);
        }
    };
    xhr.send();
}

function processCenters(parsedData) {
    const centerSelect = document.getElementById("location");
    centerSelect.innerHTML = "";
    const pleaseChoosePlaceHolderOption = document.createElement("option");
    pleaseChoosePlaceHolderOption.value = "";
    pleaseChoosePlaceHolderOption.textContent = "Bitte wählen Sie einem zentrum";
    centerSelect.appendChild(pleaseChoosePlaceHolderOption);

    parsedData.data.forEach(item => {
        const centerOption = document.createElement("option");
        centerOption.value = JSON.stringify({
            id: item.id,
            name: item.name
        });
        centerOption.textContent = item.name;
        centerSelect.appendChild(centerOption);
    });
}

function getTimeSlots(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "timeslots?id=" + id, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            processTimeSlots(JSON.parse(xhr.responseText));
        } else {
            console.error("Error retrieving data:", xhr.statusText);
        }
    };
    xhr.send();
}

function processTimeSlots(parsedTimeSlots) {
    const timeSlotTable = document.getElementById("centerTable");
    // Clear existing table content
    timeSlotTable.innerHTML = "";

    // Create table header row
    const headerRow = timeSlotTable.insertRow(-1);
    const idHeader = headerRow.insertCell(0);
    const startTimeHeader = headerRow.insertCell(1);
    const endTimeHeader = headerRow.insertCell(2);
    const capacityHeader = headerRow.insertCell(3);
    idHeader.textContent = "ID";
    startTimeHeader.textContent = "Start Time";
    endTimeHeader.textContent = "End Time";
    capacityHeader.textContent = "Capacity";

    // Populate table with time slot data
    parsedTimeSlots.data.forEach(slot => {
        const newRow = timeSlotTable.insertRow(-1);
        const idCell = newRow.insertCell(0);
        const startTimeCell = newRow.insertCell(1);
        const endTimeCell = newRow.insertCell(2);
        const capacityCell = newRow.insertCell(3);
        idCell.textContent = slot.id;
        startTimeCell.textContent = slot.start_time;
        endTimeCell.textContent = slot.end_time;
        capacityCell.textContent = slot.capacity;
    });
}

function addTimeSlotRow() {
    const centerTable = document.getElementById('centerTable').getElementsByTagName('tbody')[0];
    const newRow = centerTable.insertRow(-1);
    const idCell = newRow.insertCell(0);
    const startTime = newRow.insertCell(1);
    const endTime = newRow.insertCell(2);
    const capacity = newRow.insertCell(3);
    const submitCell = newRow.insertCell(4);

    // Get the center object from the selected option
    const centerSelect = document.getElementById("location");
    const selectedOption = centerSelect.options[centerSelect.selectedIndex];
    let centerId = '';
    if (selectedOption.value) {
        const center = JSON.parse(selectedOption.value);
        centerId = center.id; // Assuming the center object has an 'id' property
    }

    // Add input fields for Name and Location
    idCell.innerHTML = '<input type="hidden" value="' + centerId + '">';
    startTime.innerHTML = '<input type="text" placeholder="Start Time">';
    endTime.innerHTML = '<input type="text" placeholder="End Time">';
    capacity.innerHTML = '<input type="text" placeholder="Capacity">';
    submitCell.innerHTML = '<button type="button" onclick="submitTimeSlot(this)">Submit</button>';
}
// Function to generate automatic ID
// Function to handle form submission
function submitTimeSlot(button) {
    // Get the row containing the input fields
    const row = button.parentNode.parentNode;

    // Get the input field values
    const id = row.cells[0].querySelector('input').value;
    const startTime = row.cells[1].querySelector('input').value;
    const endTime = row.cells[2].querySelector('input').value;
    const capacity = row.cells[3].querySelector('input').value;

    // Construct the URL for form submission
    const url = 'submitTimeSlot'; // Replace 'submitVaccine' with the actual URL

    // Prepare the form data
    const data = 'id=' + encodeURIComponent(id) +
        '&startTime=' + encodeURIComponent(startTime) +
        '&endTime=' + encodeURIComponent(endTime) +
        '&capacity=' + encodeURIComponent(capacity);

    // Create and send a POST request with the form data
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Handle successful submission
            alert('Form submitted successfully');
            getVaccines(id);
        } else {
            // Handle submission error
            console.error('Form submission failed:', xhr.statusText);
        }
    };
    xhr.onerror = function() {
        // Handle network error
        console.error('Network error occurred');
    };
    xhr.send(data);
    console.log(typeof data);
    console.log(data);

}
document.getElementById('addTimeSlot').addEventListener('click', function() {
    addTimeSlotRow();
});