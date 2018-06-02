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
        data: '{"id":"' + id + '"}"',
        success: function (data) {
            console.log("SUCCESS: ", data);
            alert("Success");
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
    alert(status);
}