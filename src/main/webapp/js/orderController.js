'use strict';
 
orderApp.controller('OrderController', ['$scope', 'OrderService', function($scope, OrderService) {
  
  //  $scope.name = "arpan";
    var self = this;
    self.order={orderId:null,orderName:'',orderDescription:'',orderPrice:''};
    self.orders=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllOrders();
 
    function fetchAllOrders(){
        OrderService.fetchAllOrders()
            .then(
            function(d) {
                self.orders = d;
            },
            function(errResponse){
                console.error('Error while fetching Orders');
            }
        );
    }
 
    function createOrder(order){
        OrderService.createOrder(order)
            .then(
            fetchAllOrders,
            function(errResponse){
                console.error('Error while creating Order');
            }
        );
    }
 
    function updateOrder(order, id){
        OrderService.updateOrder(order, id)
            .then(
            fetchAllOrders,
            function(errResponse){
                console.error('Error while updating Order');
            }
        );
    }
 
    function deleteOrder(id){
        OrderService.deleteOrder(id)
            .then(
            fetchAllOrders,
            function(errResponse){
                console.error('Error while deleting Order');
            }
        );
    }
 
    function submit() {
        if(self.order.orderId===null){
            console.log('Saving New Order', self.order);
            createOrder(self.order);
        }else{
            updateOrder(self.order, self.order.orderId);
            console.log('Order updated with id ', self.order.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.orders.length; i++){
            if(self.orders[i].orderId === id) {
                self.order = angular.copy(self.orders[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.orders.orderId === id) {
            reset();
        }
        deleteOrder(id);
    }
 
 
    function reset(){
        self.order={orderId:null,orderName:'',orderDescription:'',orderPrice:''};
        $scope.myForm.$setPristine(); 
    }
 
}]);