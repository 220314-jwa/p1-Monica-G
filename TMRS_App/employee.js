let employeeTable = document.getElementById('petsTable');
let messageBox = document.getElementById('messageBox');

checkLogin().then(setMessageBox);

function setMessageBox() {
    if (loggedInUser) {
        if (loggedInUser.pets && loggedInUser.pets.length>0) {
            showPets(loggedInUser.pets);
        } else {
            messageBox.innerText = 'You don\'t seem to have any pets. Try adopting some!';
        }
    } else {
        messageBox.innerText = 'You need to be logged in to view your pets! (You may need to refresh the page.)';
    }
}

function showPets(petsArr) {
    petsTable.innerHTML = `<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Species</th>
    <th>Age</th>
    <th>Description</th>
    </tr>`;
    
    for (let pet of petsArr) {
        // these pets are coming from Java so the fields are the same
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${pet.id}</td>
            <td>${pet.name}</td>
            <td>${pet.species}</td>
            <td>${pet.age}</td>
            <td>${pet.description}</td>
        `;
        // add the row to the table
        petsTable.appendChild(row);
    }
}
