package com.backend.common.template;


import com.backend.common.constants.CodeEnum;
import com.backend.common.dto.ResultDTO;
import com.backend.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


/**
 * controller模板方法
 */
@Slf4j
public class BizTemplate {
    
    private BizTemplate() {}

    public static <T> ResultDTO<T> execute(BizCallBack<T> bizCallBack) {
        try {
            bizCallBack.paramCheck();
            T checkResult = bizCallBack.preCheck();
            if(Objects.nonNull(checkResult)){
                return ResultDTO.failed(checkResult);
            }
            T t = bizCallBack.execute();
            return ResultDTO.succeed(t);
        } catch (BizException e) {
            log.error("BizException     error:", e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), e.getErrMsg());
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException     error:", e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            log.error("RuntimeException     error:", e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        } catch (Exception e) {
            log.error("Exception     error:", e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        } catch (Throwable throwable) {
            log.error("Throwable     error:", throwable);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        }
    }

}
