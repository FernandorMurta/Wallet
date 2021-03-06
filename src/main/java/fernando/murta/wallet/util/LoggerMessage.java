package fernando.murta.wallet.util;

import fernando.murta.wallet.model.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Slf4j
public class LoggerMessage {

    public static final String _REQUEST = "================ REQUEST ================";

    public static final String _REQUEST_BODY = "================ REQUEST BODY ================";

    public static final String _PARAM = "================ PARAMETERS ================";


    /**
     * Method to send a INFO Log
     *
     * @param message Message to send to Log
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * Method to send a INFO Log with a Object
     *
     * @param message Message to send to Log
     * @param object  Object to send to Log
     */
    public static void info(String message, Object object) {
        log.info(message);
        if (object != null) {
            log.info(object.toString());
        }
    }

    /**
     * Method to log the Param with Name and Value
     *
     * @param params Param from request
     */
    public static void infoParam(Params params) {
        log.info(params.toString());
    }

    /**
     * Method to show all params in the request
     *
     * @param listOfParam List of Params from the request
     */
    public static void params(List<Params> listOfParam) {
        info(_PARAM);
        listOfParam.forEach(LoggerMessage::infoParam);
    }

    /**
     * Method to send a ERROR Log
     *
     * @param message Message to send to Log
     */
    public static void error(String message) {
        log.error(message);
    }

    /**
     * Method to send a ERROR Log with a Object
     *
     * @param message Message to send to Log
     * @param object  Object to send to Log
     */
    public static void error(String message, Object object) {
        log.error(message);
        log.error(object.toString());
    }

    /**
     * Method to send a Trace to the Log
     *
     * @param error Error
     */
    public static void trace(Throwable error) {
        log.trace(error.getMessage(), error);
    }


    /**
     * @author Fernando Murta
     * @version 0.0.1
     */
    @Data
    @AllArgsConstructor
    public static class Params {

        String name;

        String value;

        /**
         * Constructor for Values
         *
         * @param name  name
         * @param value Value to convert
         */
        public Params(String name, Double value) {
            this.name = name;
            this.value = new BigDecimal(value)
                    .setScale(2, RoundingMode.UP)
                    .toString();
        }

        /**
         * Method to return param when is from a list
         *
         * @param name  Name of Param
         * @param value Value of Param
         */
        public Params(String name, List<TransactionType> value) {
            this.name = name;
            this.value = value.stream().map(TransactionType::getType).collect(Collectors.joining(", "));
        }
    }
}
