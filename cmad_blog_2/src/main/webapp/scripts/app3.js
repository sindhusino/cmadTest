(function(){
	var app = angular.module('app',['ngRoute']);
	
	app.config(['$routeProvider',
	            function($routeProvider){
	$routeProvider.
	when('/users',{
	templateUrl : 'Airlines.jsp',
	controller : 'UserController'
	}).
	when('/products',{
	templateUrl : 'ProductsController.jsp',
	controller : 'ProductsController'
	}).
	otherwise({
	redirectTo: '/'
	});
	}]);
	
	app.controller('UserController',function($http, $log, $scope){
		var controller = this;
		$scope.users=[];
		$scope.loading = true;
		$log.debug("Getting users...");
		$http.get('rest/user').
		  success(function(data, status, headers, config) {
			  $scope.users = data;
			  $scope.loading = false;
		  }).
		  error(function(data, status, headers, config) {
			  $scope.loading = false;
			  $scope.error = status;
		  });
		
		$scope.addUser = function(){
			$http.post("rest/user",$scope.user).success(function(data, status, headers, config){
			$scope.users.push(angular.copy($scope.user));
			/* $scope.users.push($scope.user); */
			$scope.showAddForm=false; 
			});
			};
		$scope.editUser = function(user){
			console.log(user);
			$scope.user = user;
			$scope.showEditForm=true;
			$scope.showAddForm=false;
		}
		
		$scope.updateUser = function(user){
			$log.debug(user);
			$http.put('rest/user',user).
			  success(function(data, status, headers, config) {
				  console.log(data);
				  $scope.showEditForm=false;
			  }).
			  error(function(data, status, headers, config) {
				  $scope.error = status;
				  $scope.showEditForm=false;
			  });

		}
	});
	//==========================
	app.controller('AddController',function($http, $log, $scope){
		var controller = this;
		$scope.users=[];
		$scope.loading = true;
		$log.debug("Updating users...");
		
		$scope.addUser = function(){
			$http.post("rest/user",$scope.user).success(function(data, status, headers, config){
			$scope.users.push(angular.copy($scope.user));
			/* $scope.users.push($scope.user); */
			$scope.showAddForm=false; 
			});
			};
		$scope.editUser = function(user){
			console.log(user);
			$scope.user = user;
			$scope.showEditForm=true;
			$scope.showAddForm=false;
		}
		
		$scope.updateUser = function(user){
			$log.debug(user);
			$http.put('rest/user',user).
			  success(function(data, status, headers, config) {
				  console.log(data);
				  $scope.showEditForm=false;
			  }).
			  error(function(data, status, headers, config) {
				  $scope.error = status;
				  $scope.showEditForm=false;
			  });

		}
	});
	//===========================
	
	
	
	app.controller("ProductsController",function($scope, $http, $log){

		$scope.loadingProducts = true;
		var result = $http.get('product.json');
		result.success(function(data, status, headers, config){
		$scope.products = data;
		$scope.loadingProducts = false;
		});
		});
})();