function openForm() {
  document.getElementById("popupForm").style.display = "block";
}
 function openFormInput(){
  document.getElementById("popupFormInput").style.display = "block";
 }

function isWork() {
  document.getElementById("salary").value = ''
  document.getElementById("salary").disabled = !document.getElementById("salary").disabled;
}

// Customer table Data
var customerContainer=[];
var customerName = document.getElementById("customerName");
var customerId = document.getElementById("customerId");
var customerRelation = document.getElementById("customerRelation");
// add Product Function
function addCustomer() {
  var customerData = {
    name: customerName.value,
    numbId: customerId.value,
    relation: customerRelation.value,
    

  }
  customerContainer.push(customerData);
  clearForm();
  displayProducts();
}
function clearForm() {
  customerName.value = "";
  customerId.value = "";
  customerRelation.value = "";
 

}
function displayProducts() {
  var cartona = ``;
  for (var i = 0; i < customerContainer.length; i++) {
    cartona += `
        <tr>
        <td>${customerContainer[i].name}</td>
        <td>${customerContainer[i].numbId}</td>
        <td>${customerContainer[i].relation}</td>
   
        <td><button onclick='deleteproduct(${i})' class="btn btn-outline-danger">مسح</button></td>
        <td><button onclick='' class="btn btn-outline-primary">تعديل</button></td>
        
        </tr>`;
  }
  document.getElementById("tableBody").innerHTML = cartona;
}
function deleteproduct(index) {
  customerContainer.splice(index, 1);
  displayProducts();
}

 