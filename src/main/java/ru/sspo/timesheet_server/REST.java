package ru.sspo.timesheet_server;

public class REST {

    /*
    * HTTP - protocol
    * gPRC - protocol
    * WebSocket - protocol
    *
    * path/endpoint/resource - /timesheets
    *
    * REST - way of design your service resources
    * 1. do NOT use verbs in paths
    * /deleteTimesheets/{ig} - bad
    * DELETE timesheets/{id} - good
    *
    * 2. resources must be located in each other
    * GET user/{userId}/role/{roleId} - get role by roleId of user with userId (this is a bit wierd) (if not 404)
    *
    * How to get user with name that has substring
    * GET user?name-like="kasdha" -> if not 204 No Content (not 404)
    * GET users?sort-by=age&sort-order=desc - sort and order
    *
    * 3. It is RECOMMENDED to use plurals
    * /user - bad
    * /users - good
    *
    * 4. Separate words with "-" (hyphen)
    * GET github.com/projects/pull-requests/{id}
    *
    * 5. Use HttpStatuses wisely
    *
    * */

}
