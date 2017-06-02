/**
 * 
 */

var app=angular.module("farmerCart",[])
               .controller("cartController",function($scope,$http){
            	      
            	   $scope.getCartSize=function()
            	      {
            	    	$http.get("getCartSize.htm").success(function(data) { 
            	    		 $scope.cartSize=data.tp;
                		  });
            	       } 	
            	   $scope.getCartList=function()
            	   {
            		   $http.get("getCarts.htm").success(function(data) { 
          	    		 $scope.carts=data.carts;
          	    		 $scope.cartPrice=data.tp;
          	    		 $scope.getCartSize();
              		  }); 
            	   };
            	   
            	   
            	   $scope.updateCart=function()
            	    {
            	    	var productId=$scope.id;
            	    	var selQuantity=$scope.selQuantity;
            	    	var cartPrice=$scope.cartPrice;
            	    	$scope.ds=true;
            	    	  $http({
             			    url: 'updateCart.htm',
             			    method: 'POST',
             			    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
             			    data: {"id":productId,"selQuantity":selQuantity,"cartPrice":cartPrice},
             			    transformRequest: function(obj) {
             			        var str = [];
             			        for(var p in obj)
             			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
             			        return str.join("&");
             			      }
             			}).success(function(data) {
             				 $scope.mes=data.mes;
             				 $scope.carts=data.carts;
             				 $scope.cartPrice=data.tp;
             				
             			});
            	    };
            	   $scope.removeCart=function(cart)
            	   {
            		   var id=cart.product.productId;
            		   
            		   $http.get("deleteCart/"+id+"/").success(function(data) { 
            	    		 $scope.carts=data.carts;
            	    		 $scope.cartSize=data.tp;
            	    		 $scope.cartPrice=data.tc;
            	    		 $scope.getCartSize();
                		  }); 
            	   }
            	   
            	   $scope.cartItemDetails=function(cart)
            	    {
            	    	$scope.name=cart.product.name;
            	    	$scope.quantity=cart.product.availableQuantity;
            	    	$scope.price=cart.product.pricePerUnit;
            	    	$scope.id=cart.product.productId;
            	    	$scope.selQuantity=cart.selectedQuantity;
            	    	$scope.cartPrice=cart.totalPrice;
            	    };
            	    $scope.calculateCartPrice=function()
             	    {
             	    	$scope.cartPrice=$scope.price * $scope.selQuantity;
             	    	if($scope.selQuantity===undefined||$scope.selQuantity==null)
             	    	{
             	    	    $scope.cartBtn=true;	
             	    	}
             	    	else if($scope.selQuantity==0)
             	    	{
             	    		$scope.cartBtn=true;
             	    	}
             	    	else
             	    	{
             	    		$scope.cartBtn=false;
             	    	}
             	    };
             	   $scope.clearFields=function()
            	    {
            	    	$scope.cartPrice="";
            	    	$scope.selQuantity="";
            	    	$scope.ds=false;
            	    };
            	   
               });
               
               
               
               