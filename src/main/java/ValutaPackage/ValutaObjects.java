package ValutaPackage;

/**
 * Created by E on 23/12/2015.
 */
class ValutaResultObject{
    int UID;
    String fromVal;
    String toVal;
    Double googleResult;
    Double soapResult;

    @Override
    public String toString(){
        return new String(UID+fromVal+toVal+googleResult+soapResult);
    }

}