/**
 * 
 */


var app=angular.module('farmerProducts',['angularUtils.directives.dirPagination'])
               .controller('farmerProductsController',function($http,$scope){
            	  
            	   $scope.ds=false;
            	   $scope.cartBtn=false;
            	   $scope.getProductCategories=function(pageNo)
               	   {
               		 $http.get("getCategories.htm").success(function(data) { 
                 	       $scope.categories=data.pc;           
               		  });
               	   };
               	$scope.prices = [
               	                {id: 1, text: '< $25'},
               	                {id: 2, text: '$25 to $100'},
               	                {id: 3, text: '$100 to $200'},
               	                {id: 4, text: '> $200'}
               	              ];
            	   
            	   $scope.getAllProducts=function(pageNo)
             	    {
             	    	 var cat=$scope.categorySelected;
           	    	     var price=$scope.priceSel;
           	    	     
             	    	  if(((cat===undefined)||(cat===null))&&((price===undefined)||(price===null)))
             	    		{
	                		 $scope.current=pageNo;
	                 	     $http.get("getAllProducts/"+pageNo+"/").success(function(data) { 
	                 	    	 $scope.totalCount=data.tp;
	                 	    	 $scope.products=data.products;
	                 	    	 $scope.getCartSize();
	                 	    	 $scope.getProductCategories();
	                  		  });
             	    		}
             	    	  else
             	    		{
             	    		  var n=null;
             	    		  if(cat!=null)
             	    		  {
             	    			  n=cat.name;
             	    		  }
             	    		  
             	    		  $scope.getPrice(price);
                	    	  var price1=$scope.price1;
                	    	  var price2=$scope.price2;
              
             	    		 $http.get("getProductsByFilter/"+n+"/"+price1+"/"+price2+"/").success(function(data) { 
                    			  $scope.products=data.products;      
                    			  $scope.totalCount=data.tp;
                    		  });
             	    		}
             	    };
             	    
             	    $scope.getCartSize=function()
             	    {
             	    	$http.get("getCartSize.htm").success(function(data) { 
             	    		 $scope.cartSize=data.tp;
                 		  });
             	    }
             	   $scope.productDetails=function(p)
             	    {
             	    	$scope.name=p.name;
             	    	$scope.quantity=p.availableQuantity;
             	    	$scope.price=p.pricePerUnit;
             	    	$scope.selQuantity=1;
             	    	$scope.cartPrice=$scope.price * $scope.selQuantity;
             	    	$scope.id=p.productId;
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
             	    
             	    $scope.addToCart=function()
             	    {
             	    	var productId=$scope.id;
             	    	var selQuantity=$scope.selQuantity;
             	    	var cartPrice=$scope.cartPrice;
             	    	$scope.ds=true;
             	    	  $http({
              			    url: 'addToCart.htm',
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
              				 $scope.cartSize=data.tp;
              				 $scope.mes=data.mes;
                 	         
              			});
             	    };
             	    
             	  $scope.getPrice=function(price)
             	  {
             		
             		  if(price===undefined||price===null)
             		   {
             			 $scope.price1=0;
          			     $scope.price2=0;
             		   }
             		  else
             		   {
             			  pr=price.id;
             			  
             			  if(pr==1)
             			  {
             				$scope.price1=0;
             				$scope.price2=25;
             			  }
             			  else if(pr==2)
             			  {
             				$scope.price1=25;
             				$scope.price2=100;
             			  }
            			  else if(pr==3)
            			  {
            				  $scope.price1=100;
            				  $scope.price2=200; 
            			  }
            			  else
            			  {
            				  $scope.price1=200;
            				  $scope.price2=10000; 
            			  }
             		   }
             		  
             	  };
             	  
             	 
               });