package eu.easygoit.com.rest.service;

import eu.easygoit.exception.NextCodeServiceNotDefinedException;
import eu.easygoit.exception.RemoteNextCodeServiceNotDefinedException;
import eu.easygoit.model.IIdEntity;
import eu.easygoit.model.extendable.NextCodeModel;
import eu.easygoit.service.IRemoteNextCodeService;
import eu.easygoit.service.nextCode.INextCodeService;

/**
 * The interface Codifiable service.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 */
public interface ICodifiableService<I, T extends IIdEntity>
        extends ICrudServiceMethod<I, T> {

    /**
     * Init code generator next code model.
     *
     * @return the next code model
     */
    NextCodeModel initCodeGenerator();

    /**
     * Gets next code.
     *
     * @return the next code
     */
    String getNextCode();

    /**
     * Next code service next code service.
     *
     * @return the next code service
     * @throws NextCodeServiceNotDefinedException the next code service not defined exception
     */
    INextCodeService<NextCodeModel> nextCodeService() throws NextCodeServiceNotDefinedException;

    /**
     * Remote next code service remote next code service.
     *
     * @return the remote next code service
     * @throws RemoteNextCodeServiceNotDefinedException the remote next code service not defined exception
     */
    IRemoteNextCodeService remoteNextCodeService() throws RemoteNextCodeServiceNotDefinedException;

    /**
     * Gets next code key.
     *
     * @param initNextCode the init next code
     * @return the next code key
     */
    String getNextCodeKey(NextCodeModel initNextCode);
}
