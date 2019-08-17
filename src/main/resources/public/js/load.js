function showOrderDetails(orderId) {
    getOrderItems(orderId);
    $('#orderDetailsModal').modal('show');
}

function completeItem(orderId, itemId) {
    if (itemId !== null && itemId !== "") {
        $.ajax({
            url: '/orders/' + orderId + '/item/' + itemId,
            type: 'POST',
            dataType: 'json',
            data: {status: 'COMPLETED'},
            success: function (result) {
                $('.success-item-' + itemId).remove();
                $('.complete-item-cont-' + itemId).html(
                    '<h4 class="text-success">' + $('#itemCompleted_literal').val() + '</h4>');
                $('#orderDetailsModalContent').empty();
                getOrderItems(orderId);
            }
        });
    } else {
        return;
    }
}

function completeOrder() {
    var orderId = $('#orderDetailsModalContent').val();
    if (orderId !== null && orderId !== "") {
        $.ajax({
            url: '/orders/' + orderId + '/status',
            type: 'POST',
            dataType: 'json',
            data: {status: 'COMPLETED'},
            success: function (result) {
                location.reload(true);
            }
        });
    } else {
        return;
    }
}

function getOrderItems(orderId) {
    $.ajax({
        url: '/orders/' + orderId,
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            var items = result.items;
            var result = '';
            if(items.length > 0){
               var rowControl = 0;
               var beforeRow = '';
               var afterRow = '';
               var itemsRemaining = false;
                $.each(items, function(index, item){
                    if(rowControl === 0) {
                        beforeRow = '<div class="row ">';
                        afterRow = '';
                        rowControl = 3;
                    } else if(rowControl === 3 || index === items.length - 1) {
                        beforeRow = '';
                        afterRow = '</div">';
                    }else {
                        beforeRow = '';
                        afterRow = '';
                    }
                    var completedButton = '<button type="button" class="btn btn-info btn-lg btn-block success-item-' + item.id + '" onClick="completeItem(\'' + orderId + '\', \'' + item.id + '\')">' + $('#CompleteItem_literal').val() + '</button>';
                    if(item.status === 'COMPLETED') {
                        completedButton = '<h4 class="text-success">' + $('#itemCompleted_literal').val() + '</h4>';
                    }else {
                        itemsRemaining = true;
                    }
                    var card = '<div class="col-sm-3 pl-3 pb-3"> ' +
                        '<div class="card bg-light" style="width: 16rem;">' +
                            '<img class="card-img-top" src=".../100px180/" alt="Card image cap">' +
                            '<div class="card-body pl-1 pr-1 pt-1 pb-1">' +
                               '<h5 class="card-title">' + item.name + '</h5>' +
                               '<p class="card-text">' + item.description + '</p>' +
                               '<div class="col-12 justify-content-center complete-item-cont-' + item.id + '">' +
                                   completedButton +
                               '</div>' +
                           '</div>' +
                       '</div>' +
                    '</div>';
                    rowControl = --rowControl;
                    result = result + beforeRow + card + afterRow;
                });
            }else{
                alertFail("Empty order");
            }
            $('#exampleModalLabel').html($('#modalOrderDetailsTitle').val() + " " + orderId)
            $('#orderDetailsModalContent').html(result);
            $('#orderDetailsModal').modal('show');
            $('#orderDetailsModalContent').val(orderId);
            if(!itemsRemaining) {
                $('.btn-complete-order').removeClass('disabled');
            }
        },
        error: function (e) {
        }
    });
}
