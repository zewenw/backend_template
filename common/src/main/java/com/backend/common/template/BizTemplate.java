package com.backend.common.template;


import com.backend.common.constants.CodeEnum;
import com.backend.common.dto.ResultDTO;
import com.backend.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;


/**
 * controller模板方法
 */
@Slf4j
public class BizTemplate {
    
    private BizTemplate() {}

    public static <T> ResultDTO<T> execute(BizCallBack<T> bizCallBack) {
        try {
            bizCallBack.paramCheck();
            bizCallBack.preCheck();
            T t = bizCallBack.execute();
            return ResultDTO.succeed(t);
        } catch (BizException e) {
            log.error("BizException：{}", e.getMessage(), e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), e.getErrMsg());
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException：{}", e.getMessage());
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            log.error("RuntimeException：{}", e.getMessage(), e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        } catch (Exception e) {
            log.error("Exception：{}", e.getMessage(), e);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        } catch (Throwable throwable) {
            log.error("Throwable：{}", throwable.getMessage(), throwable);
            return ResultDTO.failed(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        }
    }

}
