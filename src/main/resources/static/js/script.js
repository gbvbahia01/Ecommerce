//==================================
//	# Loading Screen
//==================================
$(window).on('load', function () {
    $(".loading-overlay").fadeOut(600);
});

//==================================
//	# Ajax (Add To Cart)
//==================================
function addItemToCart(id) {
    $.ajax({
        url: '/cart/items', // URL ADD ITEM CART
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: '{"id_' + id + '":"' + id + '"}"',
        success: function (data) {
            console.log("SUCCESS: ", data);
           updateCartItemCount();
        },
        error: function (e) {
            console.log("ERROR: ", e);
            alert("ERROR");
        }
    });
}

//==================================
//	# Ajax (Update Cart Menu)
//==================================

function updateCartItemCount() {
    $.ajax ({
        url: '/cart/items/count',
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        complete: function(responseData, status, xhttp){
            $('#cart-item-count').text('(' + responseData.responseJSON.count + ')');
        }
    });
}