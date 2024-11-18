<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Billing Page</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous"
    />
    <script
      src="https://code.jquery.com/jquery-2.2.4.js"
      integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6">
          <form class="p-2" method="post">
            <div class="col-12">
              <label for="customerName" class="font-weight-bold"
                >Customer Name</label
              >
              <input
                type="text"
                class="form-control"
                id="customerName"
                name="customerName"
                required="required"
              />
            </div>
            <div class="col-12">
              <label
                class="font-weight-bold"
                for="itemName"
                class="font-weight-bold"
                >Select Item</label
              >
              <select
                class="form-control"
                id="itemName"
                name="itemName"
                onchange="showUnitPrice()"
                required="required"
              >
                <option value="">Select Item</option>
                <c:forEach items="${items}" var="item">
                  <option
                    value="${item.itemId}"
                    data-itemname="${item.itemName}"
                  >
                    ${item.itemName}
                  </option>
                </c:forEach>
              </select>
            </div>
            <div class="col-12">
              <label for="quantity" class="font-weight-bold">Quantity</label>
              <input
                type="number"
                class="form-control"
                id="quantity"
                name="quantity"
                min="1"
                required
                onchange="calculateTotal()"
              />
            </div>
            <div class="col-12">
              <label for="pricePerUnit" class="font-weight-bold"
                >Price per Unit</label
              >
              <input
                type="number"
                class="form-control"
                id="pricePerUnit"
                name="pricePerUnit"
                min="1"
                readonly="readonly"
              />
            </div>

            <div class="col-12">
              <label for="totalAmount" class="font-weight-bold"
                >Total Amount</label
              >
              <input
                type="text"
                class="form-control"
                id="totalAmount"
                name="totalAmount"
                readonly
              />
            </div>
            <div class="col-12">
              <label for="date" class="font-weight-bold">Date</label>
              <input
                type="date"
                class="form-control"
                id="date"
                name="date"
                required
                readonly
              />
            </div>
          </form>
          <div class="col-12">
            <div class="mt-3 text-center">
              <button class="btn btn-primary mr-2" onclick="addItemToBill()">
                Add Item
              </button>
              <button
                type="button"
                class="btn btn-success mr-2"
                onclick="saveBill()"
              >
                Save Bill
              </button>
              <button type="reset" class="btn btn-warning">Clear</button>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <a href="./summary" class="btn btn-info me-2">Go to Summary Page &rarr; </a>
        </div>
      </div>

      <!-- Table to display added items -->
      <div class="mt-4">
        <div class="h3">Added Items</div>
        <table class="table table-bordered mt-2">
          <thead>
            <tr>
              <th>Sl. No</th>
              <th>Item Name</th>
              <th>Quantity</th>
              <th>Unit Price</th>
              <th>Total Value</th>
            </tr>
          </thead>
          <tbody id="itemTableBody">
            <!-- Items will be dynamically added here -->
          </tbody>
        </table>
      </div>
    </div>

    <script type="text/javascript">
      // JavaScript to set the current date in the date input field
      document.addEventListener("DOMContentLoaded", function () {
        var currentDate = new Date().toISOString().split("T")[0]; // Get today's date in YYYY-MM-DD format
        document.getElementById("date").value = currentDate;
      });

      var billItems = []; // Array to store bill items
      var selectedPQty;

      //display unit price
      function showUnitPrice() {
        var itemId = $("#itemName").val();
        // alert(itemId);
        $.ajax({
          type: "GET",
          url: "getItemPrice",
          data: { itemId: itemId },
          success: function (response) {
            selectedPQty = response.quantity;
            // alert(response);
            console.log(response);
            $("#pricePerUnit").val(response.pricePerUnit);
          },
          error: function (xhr, status, error) {
            console.error(xhr.responseText);
          },
        });
      }

      // Function to calculate total amount
      function calculateTotal() {
        var quantity = $("#quantity").val();
        var pricePerUnit = $("#pricePerUnit").val();

        var totalAmount = quantity * pricePerUnit;
        document.getElementById("totalAmount").value = totalAmount.toFixed(2);
      }

      // Function to add item to bill using AJAX
      function addItemToBill() {
        var customerName = $("#customerName").val();
        var itemId = $("#itemName").val();
        var quantity = $("#quantity").val();
        var pricePerUnit = $("#pricePerUnit").val();
        var totalAmount = $("#totalAmount").val();
        var dateOfSales = $("#date").val();

        var itemName = $("#itemName option:selected").data("itemname");

        // Validate if all fields are filled
        if (
          customerName === "" ||
          itemId === "" ||
          quantity === "" ||
          pricePerUnit === "" ||
          totalAmount === ""
        ) {
          alert("Please fill out all fields.");
          return;
        }

        if (quantity > selectedPQty) {
          alert(
            "Available item count is " + selectedPQty + " choose fewer qty"
          );
          return;
        }

        // Create an object for the new bill item
        var newItem = {
          customerName: customerName,
          itemId: itemId,
          itemName: itemName,
          quantity: quantity,
          pricePerUnit: pricePerUnit,
          totalAmount: totalAmount,
          dateOfSales: dateOfSales,
        };

        console.log("New Item:", newItem);

        // Add the new item to the billItems array
        billItems.push(newItem);

        console.log("Updated Bill Items:", billItems);

        // Clear form fields after adding item
        $("#customerName").val("");
        $("#itemName").val("");
        $("#quantity").val("");
        $("#pricePerUnit").val("");
        $("#totalAmount").val("");

        // Update the HTML table with added items
        updateTable();
      }

      function updateTable() {
        var tableBody = document.getElementById("itemTableBody");
        tableBody.innerHTML = ""; // Clear existing table rows

        // Loop through billItems array and create table rows
        billItems.forEach(function (item, index) {
          var row =
            "<tr>" +
            "<td>" +
            (index + 1) +
            "</td>" +
            "<td>" +
            item.itemName +
            "</td>" +
            "<td>" +
            item.quantity +
            "</td>" +
            "<td>" +
            item.pricePerUnit +
            "</td>" +
            "<td>" +
            item.totalAmount +
            "</td>" +
            "</tr>";
          tableBody.insertAdjacentHTML("beforeend", row);
        });
      }

      // Function to save the bill
      function saveBill() {
        if (billItems.length <= 0) {
          alert("no items in cart");
          return;
        }

        $.ajax({
          type: "POST",
          url: "saveBill", // Replace with your backend endpoint URL
          contentType: "application/json",
          data: JSON.stringify(billItems), // Send the bill items array as JSON
          success: function (response) {
            console.log("Bill saved successfully:", response);
            alert("Bill saved successfully!");
            clearForm(); // Optionally clear the form after saving
          },
          error: function (xhr, status, error) {
            console.error("Error saving bill:", xhr.responseText);
            alert("Failed to save bill. Please try again.");
          },
        });
      }

      // Function to clear form fields and table
      function clearForm() {
        // Clear input fields
        $("#customerName").val("");
        $("#itemName").val(""); // Reset the selected item to the default option if needed
        $("#quantity").val("");
        $("#pricePerUnit").val("");
        $("#totalAmount").val(""); // Clear total amount if needed

        // Clear the billItems array
        billItems = [];

        // Clear the table
        var tableBody = document.getElementById("itemTableBody");
        tableBody.innerHTML = "";
      }
    </script>
  </body>
</html>
