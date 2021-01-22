package carbook.entity;


public enum ResponseStatusEnum {
	SUCCESS(200),
	PARAMS_INVALID(400),
	NOT_FOUND(404),
	TOKEN_EXPIRED(401),
	UNAUTHORIZED(410),
	DEVICE_NOT_FOUND(412),
	NOT_CHECKIN(413),
	FAIL(500),
	PARAM_DATA_INVALID(400),
	DATA_INVALID(400),
	LICENSE_EXPIRED(414),
	WORKING_MODE_NOT_VALID(415),
	WAITING_CONFIRM(205),
	USER_IS_NOT_VERIFIED(499);
	
	private final int value;
    private ResponseStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}