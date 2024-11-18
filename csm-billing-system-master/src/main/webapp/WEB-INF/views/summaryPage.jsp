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
      <a href="./billing" class="btn btn-info me-2"> &larr; Go to Billing Page</a>

      <form class="mt-4">
        <div class="form-group">
          <label for="selectedDate">Select Date:</label>
          <input
            type="date"
            class="form-control"
            id="selectedDate"
            value="${selectedDate}"
            onchange="fetchCustomerBilling()"
          />
        </div>
      </form>
      <table class="table mt-3 table-bordered">
        <thead>
          <tr>
            <th>Sl. No</th>
            <th>Customer Name</th>
            <th>Sale Quantity</th>
            <th>Total Billed Amount</th>
          </tr>
        </thead>
        <tbody id="customerBillingTable">
          <!-- Table rows will be dynamically added here -->
        </tbody>
        <tfoot>
          <tr>
            <th colspan="3">Total amount</th>
            <th id="totalAmount"></th>
          </tr>
        </tfoot>
      </table>
    </div>
    <script>
      $(document).ready(function () {
        fetchCustomerBilling(); // Fetch initial data on page load
      });

      function fetchCustomerBilling() {
        var selectedDate = $("#selectedDate").val();

        $.ajax({
          type: "GET",
          url: "customerBilling",
          data: { date: selectedDate },
          success: function (data) {
            $("#customerBillingTable").empty();
            var totalAmount = 0;
            console.log(data);

            data.forEach(function (item, index) {
              var row =
                "<tr>" +
                "<td>" +
                (index + 1) +
                "</td>" +
                "<td>" +
                item.customerName +
                "</td>" +
                "<td>" +
                item.numOfItems +
                "</td>" +
                "<td>" +
                item.totalAmount.toFixed(2) +
                "</td>" +
                "</tr>";
              $("#customerBillingTable").append(row);

              totalAmount += item.totalAmount;
            });

            $("#totalAmount").text(totalAmount.toFixed(2));
          },
          error: function (xhr, status, error) {
            console.error(xhr.responseText);
          },
        });
      }
    </script>
  </body>
</html>
