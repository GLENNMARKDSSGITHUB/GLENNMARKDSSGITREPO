/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.registration;

import com.dss.dto.user.UsersDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service interface for DSS Registration
 */

public interface RegistrationService {

    /** Returns a String value if the admin user successfully registers the account or not.
     * @param user userDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #registerAccount(UsersDTO)
     */
    DssCommonMessageDetails registerAccount(UsersDTO user);

    /** Returns a list of Users
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #displayRegistrations()
     */
    DssCommonMessageDetails displayRegistrations();

    /** Returns a specific user record the form of a List <Users>
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #searchRegistrationByEmail(String)
     */
    DssCommonMessageDetails searchRegistrationByEmail(String email);

    /** Returns a String value if the admin user successfully changes the account password or not.
     * @param email User's email
     * @param newPassword User's new password
     * @param confirmPassword User's confirmation password
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #changePassword(String,String,String)
     */
    DssCommonMessageDetails changePassword(String email, String newPassword, String confirmPassword);

    /** Returns a String value if the admin user successfully deletes the account or not.
     * The user requires to enter the email and password to delete the account.
     * @param email User's email
     * @param password User's password
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #changePassword(String,String,String)
     */
    DssCommonMessageDetails deleteAccount(String email, String password);
}
