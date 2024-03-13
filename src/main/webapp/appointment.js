 function init() {
//
//     var x = document.getElementById("div_add_user");
//     x.style.display = "none";
//
    loadUsers();
}
//
// function showform() {
//     var x = document.getElementById("div_add_user");
//     if (x.style.display === "none") {
//         x.style.display = "block";
//     } else {
//         x.style.display = "none";
//     }
//
//     document.getElementById("fname").value = "";
//     document.getElementById("lname").value = "";
//     document.getElementById("nic").value = "";
//     document.getElementById("mobile").value = "";
//     document.getElementById("user_type").value = "insert";
//
// }

// function save() {
//
//     let fname = document.getElementById("fname").value;
//     let lname = document.getElementById("lname").value;
//     let nic = document.getElementById("nic").value;
//     let mobile = document.getElementById("mobile").value;
//     let id = document.getElementById("id").value;
//
//     if (fname == null) {
//         alert("First Name is Empty!");
//     } else if (lname == null) {
//         alert("Last Name is Empty!");
//     } else if (nic == null) {
//         alert("NIC Name is Empty!");
//     } else if (mobile == null) {
//         alert("Mobile Name is Empty!");
//     } else {
//
//         if (document.getElementById("user_type").value == "insert") {
//
//             xmlShop = new XMLHttpRequest();
//             xmlShop.onreadystatechange = function () {
//                 if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
//                     var resptext = this.responseText;
//                     console.log("Data >> " + resptext);
//                     var dataArray = JSON.parse(resptext);
//                     console.log(dataArray.status);
//
//                     alert(dataArray.message);
//
//                     if (dataArray.status == 200) {
//                         document.getElementById("fname").value = "";
//                         document.getElementById("lname").value = "";
//                         document.getElementById("nic").value = "";
//                         document.getElementById("mobile").value = "";
//
//                         loadUsers();
//                     }
//
//                 }
//             };
//             xmlShop.open("POST", "saveUser", true);
//             xmlShop.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//             xmlShop.send("fname=" + fname + "&lname=" + lname + " &nic=" + nic + " &mobile=" + mobile);
//
//         } else {
//
//             xmlShop = new XMLHttpRequest();
//             xmlShop.onreadystatechange = function () {
//                 if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
//                     var resptext = this.responseText;
//                     console.log("Data >> " + resptext);
//                     var dataArray = JSON.parse(resptext);
//                     console.log(dataArray.status);
//
//                     alert(dataArray.message);
//
//                     if (dataArray.status == 200) {
//                         document.getElementById("fname").value = "";
//                         document.getElementById("lname").value = "";
//                         document.getElementById("nic").value = "";
//                         document.getElementById("mobile").value = "";
//                         document.getElementById("id").value = "";
//
//                         loadUsers();
//                     }
//
//                 }
//             };
//             xmlShop.open("POST", "updateUser", true);
//             xmlShop.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//             xmlShop.send("fname=" + fname + "&lname=" + lname + " &nic=" + nic + " &mobile=" + mobile + "&id=" + id);
//
//         }
//     }
// }
//

 function loadUsers() {
     xmlShop = new XMLHttpRequest();
     xmlShop.onreadystatechange = function () {
         if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
             var resptext = this.responseText;
             console.log("Data >> " + resptext);
             var dataArray = JSON.parse(resptext);
             console.log(dataArray.status);

             // alert(dataArray.message);

             if (dataArray.status == 200) {
                 if (dataArray.data.length > 0) {
                     let value = '';
                     for (var item in dataArray.data) {
                         value += '<tr>'
                         value += '<td>' + dataArray.data[item].id + '</td>'
                         value += '<td>' + dataArray.data[item].firstname + '</td>'
                         value += '<td>' + dataArray.data[item].lastname + '</td>'
                         value += '<td>' + dataArray.data[item].impfort + '</td>'
                         value += '<td>' + dataArray.data[item].impfung + '</td>'
                         value += '<td>' + dataArray.data[item].date + '</td>'
                         value += '<td>' + dataArray.data[item].time + '</td>'
                         value += '<td>';
                         value += ' <button class="button" onclick="deleteUsers(' + dataArray.data[item].id + ')">Delete</button>';
                         value += '</td>';
                         value += '</tr>'
                     }
                     document.getElementById("tbody").innerHTML = value;
                 } else {
                     document.getElementById("noUsersMessage").innerHTML = "Sie haben noch kein Termin gebucht";
                 }
             }
         }
     };
     xmlShop.open("GET", "get", true);
     xmlShop.send();
 }

//
// function editUsers(id) {
//     document.getElementById("user_type").value = "update";
//
//     xmlShop = new XMLHttpRequest();
//     xmlShop.onreadystatechange = function () {
//         if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
//             var resptext = this.responseText;
//             console.log("Data >> " + resptext);
//             var dataArray = JSON.parse(resptext);
//             console.log(dataArray.status);
//
//             // alert(dataArray.message);
//
//             if (dataArray.status == 200) {
//
//                 document.getElementById("id").value = dataArray.data.id;
//                 document.getElementById("fname").value = dataArray.data.fname;
//                 document.getElementById("lname").value = dataArray.data.lname;
//                 document.getElementById("nic").value = dataArray.data.nic;
//                 document.getElementById("mobile").value = dataArray.data.mobile;
//
//                 var x = document.getElementById("div_add_user");
//                 x.style.display = "block";
//
//             }
//
//         }
//     };
//     xmlShop.open("GET", "getUserById?id=" + id, true);
//     xmlShop.send();
// }
//
function deleteUsers(id) {

    xmlShop = new XMLHttpRequest();
    xmlShop.onreadystatechange = function () {
        if ((xmlShop.readyState == 4) && (xmlShop.status == 200)) {
            var resptext = this.responseText;
            console.log("Data >> " + resptext);
            var dataArray = JSON.parse(resptext);
            console.log(dataArray.status);

            alert(dataArray.message);
            loadUsers();

        }
    };
    xmlShop.open("GET", "delete?id=" + id, true);
    xmlShop.send();
}
