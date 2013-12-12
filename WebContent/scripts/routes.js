/************************************************************************
 * 
 * 	FileName	: routes.js
 *  
 *  Description : Set up our mappings between URLs, templates, and 
 *  			  controllers.
 *  			  
 *  Revision History:
 *  ---------------------------------------------------------------------
 *  	DATE		 NAME			MODULE 			Changes
 *  ---------------------------------------------------------------------
 *  10-12-2013	Gorav Dhiman	Angular JS		 	Routes for app added		
 *  							Framework
 *  
 ************************************************************************/

/************************************************************************
 *  Function 	: emailRouteConfig
 *	Description : This is to provide a routehandler for all http request 
 * 	Inputs 		: Route Provider.
 * 	Outputs		: 
 ************************************************************************/

function emailRouteConfig($routeProvider) {

$routeProvider.
when('/', {
controller: ListController,
templateUrl: 'loginForm.html'
}).
// Notice that for the detail view, we specify a parameterized URL component
// by placing a colon in front of the id
when('/def.html', {
controller: DetailController,
templateUrl: 'def.html'
}).
otherwise({
	controller: ListController,
	templateUrl: 'list.html'
	//redirectTo: 'list.html'
});
}
