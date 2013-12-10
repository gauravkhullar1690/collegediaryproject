/************************************************************************
 * 
 * 	FileName	: controller.js
 *  
 *  Description : Set up our mappings between URLs, templates, and 
 *  			  controllers.
 *  			  
 *  Revision History:
 *  ---------------------------------------------------------------------
 *  	DATE		 NAME			MODULE 			Changes
 *  ---------------------------------------------------------------------
 *  10-12-2013	Gorav Dhiman	Angular JS		 	Controller for app added		
 *  							Framework
 *  
 ************************************************************************/

/************************************************************************
 *  Function 	: ListController
 *	Description : Publish our messages for the list template.It is called
 *				  when request is mapped to list.html 
 * 	Inputs 		: Scope,Directive
 * 	Outputs		: 
 ************************************************************************/

function ListController($scope,messages) {
	$scope.messages = messages.query();

}

/************************************************************************
 *  Function 	: ListController
 *	Description : Get the message id from the route (parsed from the URL) 
 *				  and use it to find the right message object it is called
 *				  when request is mapped to detail.html 
 * 	Inputs 		: Scope
 * 	Outputs		: 
 ************************************************************************/

function DetailController($scope) {
//	$scope.message = (messages.query())[$routeParams.id]
	$scope.message = messages[0];
}

/************************************************************************
 *  Function 	: loginController
 *	Description : Called when login form submit.
 * 	Inputs 		: Scope,http & location & fetch all user list
 * 	Outputs		: 
 ************************************************************************/

function logincontroller($scope,$http,$location)
{
	var resmessage={};
	$scope.login =function(){
		var url = "rest/user/findUser";
        /* while compiling form , angular created this object*/
        var data=$scope.user;  
        /* post to server*/
        $http.post(url, data).success(function(response) {
            $scope.status = status;
            $scope.message = response[0];
            //$location.path("list.html");
            return response;
        }).error(function(data, status, headers, config) {
                console.log(status, headers, config);
        });	
    };
}