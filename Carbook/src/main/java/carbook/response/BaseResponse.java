
package carbook.response;

import carbook.entity.ResponseStatusEnum;

public class BaseResponse {
	private int status;
    private String message;
    private Object data;
    
    public BaseResponse() {
		this.setStatus(ResponseStatusEnum.SUCCESS);
		this.setMessage(ResponseStatusEnum.SUCCESS);
		this.setData(null);
    }
    
	public int getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusEnum statusEnum) {
		this.status = statusEnum.getValue();
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(ResponseStatusEnum statusEnum) {
		switch(statusEnum) {
		case SUCCESS:
			this.message = "Thành công";
			break;
		case PARAMS_INVALID:
			this.message = "Dữ liệu không hợp lệ";	
			break;
		case NOT_FOUND:
			this.message = "Not found";	
			break;
		case TOKEN_EXPIRED:
			this.message = "Token expired";	
			break;
		case UNAUTHORIZED:
			this.message = "Không có quyền truy cập chức năng này";
			break;
		case DEVICE_NOT_FOUND:
			this.message = "Device not found";
			break;
		case FAIL:
			this.message = "Error";	
			break;
		case NOT_CHECKIN:
			this.message = "Not checkin";	
			break;
		case DATA_INVALID:
			this.message = "Dữ liệu không hợp lệ";	
			break;
		default:
			this.message = "Not found";
			break;
		}
	}
	
	public void setMessageError(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
