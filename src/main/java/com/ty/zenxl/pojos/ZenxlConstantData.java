package com.ty.zenxl.pojos;

/**
 * Contains all the custom constants used throughout the application.
 * 
 * @author Indrajit
 * @version 1.0
 */

public class ZenxlConstantData {

	private ZenxlConstantData() {
	}

	public static final String PASSCODE_NOT_FOUND_WITH_ENTERED_EMAIL = "Passcode not found with entered email.";
	public static final String ENTERED_PASSCODE_NOT_VALID = "Entered passcode not valid.";
	public static final String PASSWORD_RESET_SUCCESSFUL = "Password reset successful.";
	public static final String OLD_PASSWORD_AND_NEW_PASSWORD_SHOULD_BE_DIFFERENT = "Old password and new password should be different.";
	public static final String BOTH_PASSWORDS_SHOULD_BE_SAME = "Both passwords should be same.";
	public static final String PASSCODE_HAS_BEEN_SENT = "Passcode has been sent , Please check your mail.";

	public static final String USER_DOESN_T_EXIST_WITH_THE_ENTERED_EMAIL = "User doesn't exist with the entered email.";
	public static final String USER_NOT_FOUND_WITH_THIS_EMAIL_ADDRESS = "User not found with this email address.";

	public static final String SIGN_UP_UNSUCCESSFUL = "Sign up unsuccessful.";
	public static final String SIGN_UP_SUCCESSFUL = "Sign up Successful.";
	public static final String EMAIL_ALREADY_EXISTS = "Email already exists.";
	public static final String LOGIN_SUCCESSFUL = "Login successful.";
	public static final String USERNAME_ALREADY_EXISTS = "Username already exists.";
	public static final String USER_NOT_FOUND_WITH_USER_ID = "User not found with user id ";
	public static final String INCORRECT_EMAIL_AND_PASSWORD = "Incorrect email and password.";
	public static final String CUSTOMER_NOT_FOUND_WITH_CUSTOMER_ID = "Customer not found with customer id ";
	public static final String USER_NOT_FOUND_WITH_THE_EMAIL = "User not found with the email ";
	public static final String ONLY_REGISTERED_ADMIN_CAN_ADD_USERS = "Only registered admin can add users.";
	public static final String THE_FIRST_REGISTERED_USER_MUST_BE_ADMIN_ONLY = "The First Requested User For Registration Must Be An Admin Only";

	public static final String ACCOUNT_IS_CURRENTLY_LOCKED = "Account is currently locked.";
	public static final String ACCOUNT_IS_CURRENTLY_INACTIVE = "Account is currently inactive.";
	public static final String USER_STATUS_CHANGED_SUCCESSFULLY = "User status changed successfully.";
	public static final String UNABLE_TO_CHANGE_USER_STATUS = "Unable to change user status";

	public static final String ADDED_SUCCESSFULLY = "Added Successfully.";
	public static final String UPDATED_SUCCESSFULLY = "Updated Successfully";
	public static final String DELETED_SUCCESSFULLY = "Deleted successfully";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong.";
	public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";

	public static final String STATUS_ALEADY_EXISTS_WITH_THE_MENTIONED_STATUS_CATEGORY = "Status aleady exists with the mentioned status catagory";
	public static final String STATUS_NOT_FOUND_WITH_THE_MENTIONED_CATEGORY = "Status not found with the mentioned category";
	public static final String STATUS_NOT_FOUND_WITH_STATUS_NAME = "Status not found with status name ";
	public static final String STATUS_CHANGED_SUCCESSFULLY = "Status changed successfullly";
	public static final String STATUS_NOT_FOUND = "Status not found";

	public static final String HS_CODE_NOT_FOUND_WITH_HS_CODE_ID = "HsCode not found with hsCode id ";
	public static final String INSPECTION_TYPE_NOT_FOUND_WITH_INSPECTION_TYPE_ID = "InspectionType not found with inspectionType id ";
	public static final String INCOTERM_TYPE_NOT_FOUND_WITH_INCOTERM_TYPE_ID = "IncotermType not found with incotermType id ";
	public static final String CODE_TYPE_NOT_FOUND_WITH_CODE_TYPE_ID = "CodeType not found with codeType id ";
	public static final String CERTIFICATE_TYPE_NOT_FOUND_WITH_CERTIFICATE_TYPE_ID = "CertificateType not found with certificateType id ";

	public static final String CODE_TYPE_ALREADY_EXISTS = "Code type already exists";
	public static final String CERTIFICATE_TYPE_ALREADY_EXISTS = "Certificate type already exists";
	public static final String INCOTERM_TYPE_ALREADY_EXISTS = "Incoterm type already exists";
	public static final String INSPECTION_TYPE_ALREADY_EXISTS = "Inspection type already exists";
	public static final String HS_CODE_ALREADY_EXISTS = "HsCode Already Exists";

	public static final Boolean IS_ERROR_FALSE = false;
	public static final Boolean IS_ERROR_TRUE = true;
	public static final String IS_ERROR = "isError";
	public static final String VALIDATION_ERROR = "validationError";
	public static final String REQUIRED_REQUEST_PART_NOT_FOUND = "Required request part not found";
	public static final String REQUIRED_REQUEST_HEADER_NOT_FOUND = "Required request header not found";

	public static final String ITEM_ADDED_SUCCESSFULLY = "Item added successfully";
	public static final String ITEM_ALREADY_EXISTS = "Item already exists";
	public static final String ITEM_NOT_FOUND_WITH_SERIAL = "Item not found with serial number: ";
	public static final String ITEM_FETCHED_SUCCESSFULLY = "Item fetched successfully";
	public static final String NO_ITEM_FOUND = "No item found";
	public static final String ALL_ITEMS_FETCHED = "Item list fetched successfully";
	public static final String ITEM_DELETED_SUCCESSFULLY = "Item deleted successfully";
	public static final String ITEM_UPDATED_SUCCESSFULLY = "Item updated successfully";

	public static final String CERTIFICATE_NOT_FOUND = "Certificate not found";
	public static final String CERTIFICATE_UPLOADED_SUCCESSFULLY = "Certificate uploaded successfully";
	public static final String CERTIFICATE_UPDATED_SUCCESSFULLY = "Certificate updated successfully";
	public static final String CERTIFICATE_ALREADY_EXISTS = "Certificate already exists, please enter different certificate number";
	public static final String PLEASE_SELECT_DOCUMENT = "Please select a document";
	public static final String DATA_NOT_EMPTY = "Data should not be empty";
	public static final String FILE_NOT_PRESENT = "File not present";
	public static final String REQUEST_CANNOT_BE_EMPTY = "Please enter the details";
	public static final String PLEASE_ENTER_CERTIFICATE_NUMBER = "Please enter certificate number";

	public static final String INVOICE_UPDATED_SUCCESSFULLY = "Invoice updated successfully";
	public static final String REQUESTED_SERIAL_NUMBER_IS_ALREADY_EXISTING = "Requested serial number is already existing";
	public static final String REQUESTED_INTERNAL_ORDER_NUMBER_IS_ALREADY_EXISTING = "Requested internal order number is already existing";
	public static final String REQUESTED_WAYBILL_NUMBER_ALREADY_EXISTS = "Requested Waybill number already exists";
	public static final String INVOICE_DELETED_SUCCESSFULLY = "Invoice deleted successfully";
	public static final String INVOICE_FETCED_SUCCESSFULLY = "Invoice fetched successfully";
	public static final String INVOICE_ALREADY_EXISTS = "Invoice already exists";
	public static final String INVOICE_LIST_FETCHED_SUCCESSFULLY = "Invoice list fetched successfully";
	public static final String INVOICE_NOT_FOUND = "Invoice not found";
	public static final String INVOICE_ADDED_SUCCESSFULLY = "Invoice added successfully";

	public static final String SHIPMENT_ITEM_ALREADY_EXISTS = "Shipment item already exists";
	public static final String SHIPMENT_DETAILS_ALREADY_EXISTS = "Shipment details already exists";
	public static final String SHIPMENT_ITEM_ADDED_SUCCESSFULLY = "Shipment item added successfully";
	public static final String SHIPMENT_ITEM_FETCHED_SUCCESSFULLY = "Shipment item fetched successfully";
	public static final String ALL_SHIPMENT_ITEMS_FETCHED_SUCCESSFULLY = "Shipment list fetched successfully";
	public static final String SHIPMENT_ITEM_NOT_FOUND = "Shipment item not found";
	public static final String SHIPMENT_ITEM_UPDATED_SUCCESSFULLY = "Shipment item updated successfully";
	public static final String SHIPMENT_ITEM_DELETED_SUCCESSFULLY = "Shipment item deleted successfully";

	public static final String BILL_OF_ENTRY_DELETED_SUCCESSFULLY = "Bill of entry deleted successfully";
	public static final String BILL_OF_ENTRY_UPDATED_SUCCESSFULLY = "Bill of entry updated successfully";
	public static final String ALL_BILL_OF_ENTRIES_ARE_FETCHED_SUCCESSFULLY = "All Bill of entries are fetched successfully";
	public static final String BILL_OF_ENTRY_NOT_FOUND = "Bill of entry not found";
	public static final String BILL_OF_ENTRY_FETCHED_SUCCESSFULLY = "Bill of entry fetched successfully";
	public static final String BILL_OF_ENTRY_ADDED_SUCCESSFULLY = "Bill of entry added successfully";

}
