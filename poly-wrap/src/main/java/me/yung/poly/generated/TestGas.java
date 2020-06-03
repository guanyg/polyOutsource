package me.yung.poly.generated;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class TestGas extends Contract {
    public static final String BINARY = "6080604052600160008190558055600280546001600160a01b0319908116309081179092556003805490911690911790556117418061003f6000396000f3fe60806040526004361061003f5760003560e01c8063775a8f5e14610041578063855a0fe3146100775780638822492214610099578063aeee48c0146100b9575b005b34801561004d57600080fd5b5061006161005c36600461139a565b6100ce565b60405161006e9190611531565b60405180910390f35b34801561008357600080fd5b5061008c610109565b60405161006e91906115e4565b3480156100a557600080fd5b5061003f6100b4366004611284565b61010f565b3480156100c557600080fd5b5061008c61020c565b60606020604051818152601f19601f83011681016020016040529080156100fc576020820181803883390190505b5060208101929092525090565b60015481565b600160005b87518110156101cb576101256110d8565b61014489838151811061013457fe5b6020026020010151600080610212565b815261015588838151811061013457fe5b602082015261016987838151811061013457fe5b604082015261017d86838151811061013457fe5b606082015284828151811061018e57fe5b602002602001015160808201526101aa84838151811061013457fe5b60a08201528280156101c057506101c081610295565b925050600101610114565b507fc0971de8d615669ce336a81fbc0043ac15834948dd39e24e22f73d3304db2e15816040516101fb9190611542565b60405180910390a150505050505050565b60005481565b61021a61112b565b602084518161022557fe5b061561024c5760405162461bcd60e51b815260040161024390611594565b60405180910390fd5b8161025957838152610278565b606060208551016040518181838960046101c2fa509081016040528252505b8215156020820152610289846103c3565b60408201529392505050565b600061029f61112b565b6102b6836060015184608001518560a001516103e6565b90506000805160206116df833981519152815182604001516040516102dc929190611561565b60405180910390a16102ec61112b565b6102ff8285602001518660a00151610476565b90506000805160206116df833981519152815182604001516040516103259291906115b4565b60405180910390a161033561112b565b610350610346838760400151610499565b8660a0015161065c565b90506000805160206116df833981519152815182604001516040516103769291906115a4565b60405180910390a16000805160206116df8339815191528551518651604001516040516103a49291906115c4565b60405180910390a16103b985518260006106ae565b1595945050505050565b6000806020830151905060208351036008026103de826107d3565b019392505050565b6103ee61112b565b82600114156103fe57508261046f565b8261042c576104256040518060400160405280602081526020016001815250600080610212565b905061046f565b61043461112b565b61044385600186901d856103e6565b9050610450818285610476565b9050600284076001141561046c57610469818685610476565b90505b90505b9392505050565b61047e61112b565b61049161048b85856109c4565b8361065c565b949350505050565b6104a161112b565b6104a961112b565b604051806060016040528060405160408082018152602080835260008184018190529284528301829052918201529091508401511580156104ec57508260400151155b156104f8579050610656565b836040015161050a5782915050610656565b826040015161051c5783915050610656565b606060008061052d878760006106ae565b9050866020015180610540575085602001515b156106095786602001518015610557575085602001515b156105a2576000811261057f57610575875187518960400151610a1a565b9093509150610596565b610590865188518860400151610a1a565b90935091505b60016020860152610604565b80600114156105cd576105b787518751610b49565b9093509150602087015115156020860152610604565b8060001914156105f8576105e386518851610b49565b90935091506020870151156020860152610604565b83945050505050610656565b61064b565b6000811261062c57610622875187518960400151610a1a565b9093509150610643565b61063d865188518860400151610a1a565b90935091505b600060208601525b509083526040830152505b92915050565b61066461112b565b61066c61112b565b60405180606001604052806040518060400160405280602081526020016001815250815260200160001515815260200160018152509050610491848285610c44565b60006001821561072757846020015180156106ca575083602001515b156106d85750600019610727565b84602001511580156106f05750836020015115156001145b156106ff57600191505061046f565b84602001511515600114801561071757508360200151155b156107275760001991505061046f565b83604001518560400151111561073e57905061046f565b84604001518460400151111561075857600003905061046f565b600080808080895151905060208a510194506020895101935060005b818110156107c2578086015193508085015192508284111561079f575094955061046f945050505050565b838311156107ba57866000190297505050505050505061046f565b602001610774565b5060009a9950505050505050505050565b600160801b680100000000000000006401000000006201000061010060106004600260001989019081041790810417908104179081041790810417908104179081041790810417600101906000906040517ff8f9cbfae6cc78fbefe7cdc3a1793dfcf4f0e8bbd8cec470b6a28a7a5a3e1efd81527ff5ecf1b3e9debc68e1d9cfabc5997135bfb7a7a3938b7b606b5b4b3f2f1f0ffe60208201527ff6e4ed9ff2d6b458eadcdf97bd91692de2d4da8fd2d0ac50c6ae9a827252361660408201527fc8c0b887b0a8a4489c948c7f847c6125746c645c544c444038302820181008ff60608201527ff7cae577eec2a03cf3bad76fb589591debb2dd67e0aa9834bea6925f6a4a2e0e60808201527fe39ed557db96902cd38ed14fad815115c786af479b7e8324736353433727170760a08201527fc976c13bb96e881cb166a933a55e490d9d56952b8d4e801485467d236242260660c08201527f753a6d1b65325d0c552a4d1345224105391a310b29122104190a11030902010060e082015261010081016040527e818283848586878898a8b8c8d8e8f929395969799a9b9d9e9faaeb6bedeeff600160f81b8082870204818160ff0385015104600160ff1b8611610100020195505050506000198201821615905080156109b257508215155b156109be578160010191505b50919050565b6109cc61112b565b6109d883836000610cf0565b90506109e6838360016106ae565b15610a0f576109f361112b565b6109ff84846001610cf0565b9050610a0b8282610dca565b9150505b61046f816002610f52565b60606000606059600019600088518901885189018a5160208601018b515b8015610ada5783518c518e5103821160018114610a78578682018452600187148289141660018114610a6d5760009750610a72565b600197505b50610ac7565b8451878184010185528781038903831160018114610ab9576000821160008a11178a85141660018114610aae5760009950610ab3565b600199505b50610abe565b600198505b50602086039550505b5050601f19938401939182019101610a38565b50831560018114610aee5760018252610af5565b6020870196505b50859650836020028c5101875260208751018701604052505050505050600060208201519050610100850681901c60011480610b315750806001145b15610b3d578460010194505b50959294509192505050565b6060600081815960001987518751808203828b01828b0184870160208101865b8015610bef57845186821160018114610ba6578c8203855260018d14600083141660018114610b9b5760009d50610ba0565b60019d505b50610bdc565b85518d81840303865260018e148c8214168e820184101760018114610bce5760009e50610bd3565b60019e505b50602087039650505b5050601f19948501949283019201610b69565b506020820191505b600082511415610c1857602089019850602087039650602082019150610bf7565b889a50868b52806040525050505050505050506000610c36836103c3565b929792965091945050505050565b610c4c61112b565b826020015115610c6e5760405162461bcd60e51b8152600401610243906115d4565b6060610c7e855185518551611009565b905060006020820151905060016020835181610c9657fe5b040361010002610ca5826107d3565b83855201905060208601511580610cce575085602001518015610cce5750610ccc856110c4565b155b610cd9576001610cdc565b60005b151560208401526040830152509392505050565b610cf861112b565b610d0061112b565b6040518060600160405280604051604080820181526020808352600281840181905292845260009084018190529201529091508060608515610d4b57610d468888610dca565b610d55565b610d558888610499565b945060008560400151600281029450600160ff86161b935090506040516040818101905260206001610100870401810282528082018590529092508251018201604052610da061112b565b8281526000602082015260408101859052610dbc878783610c44565b9a9950505050505050505050565b610dd261112b565b610dda61112b565b604051806060016040528060405180604001604052806020815260200160008152508152602001600015158152602001600081525090506060600080610e22878760006106ae565b9150866020015180610e35575085602001515b15610ef95786602001518015610e4c575085602001515b15610e9f578160011415610e7757610e6687518751610b49565b600160208801529093509050610e9a565b8160001914156105f857610e8d86518851610b49565b6000602088015290935090505b610ef4565b60008212610ec257610eb8875187518960400151610a1a565b9093509050610ed9565b610ed3865188518860400151610a1a565b90935090505b8660200151610ee9576000610eec565b60015b151560208601525b610f42565b8160011415610f1f57610f0e87518751610b49565b600060208801529093509050610f42565b8160001914156105f857610f3586518851610b49565b6001602088015290935090505b9184525060408301525092915050565b610f5a61112b565b6060600061010084900381808080895151601f1992509050808a51019250601f1981015b82811015610fc05783519650801560018114610fa05760208503519550610fa5565b600095505b5093851b95891c86811784529593601f199384019301610f7e565b506020830192505b600083511415610fe25760209290920191601f1901610fc8565b601f198301818152808b5296508860408b01510360408b0152509798975050505050505050565b606083518351835160405183815282602082015281604082015283606082018560208b0160046101c2fa84606001848184018660208c0160046101c2fa91508401838382018160208b0160046101c2fa915081801561106757611069565bfe5b5083018360608401828560056105465a03fa9150818015611067575083836060015b6001600082511414156110a757601f199091019060200161108b565b601f19019081529290930160600160405250979650505050505050565b600081515182510160028151069392505050565b6040518060c001604052806110eb61112b565b81526020016110f861112b565b815260200161110561112b565b815260200161111261112b565b81526020016000815260200161112661112b565b905290565b604051806060016040528060608152602001600015158152602001600081525090565b600082601f83011261115f57600080fd5b813561117261116d8261161d565b6115f2565b81815260209384019390925082018360005b838110156111b0578135860161119a888261122a565b8452506020928301929190910190600101611184565b5050505092915050565b600082601f8301126111cb57600080fd5b81356111d961116d8261161d565b915081818352602084019350602081019050838560208402820111156111fe57600080fd5b60005b838110156111b057816112148882611279565b8452506020928301929190910190600101611201565b600082601f83011261123b57600080fd5b813561124961116d8261163e565b9150808252602083016020830185838301111561126557600080fd5b611270838284611681565b50505092915050565b8035610656816116c7565b60008060008060008060c0878903121561129d57600080fd5b863567ffffffffffffffff8111156112b457600080fd5b6112c089828a0161114e565b965050602087013567ffffffffffffffff8111156112dd57600080fd5b6112e989828a0161114e565b955050604087013567ffffffffffffffff81111561130657600080fd5b61131289828a0161114e565b945050606087013567ffffffffffffffff81111561132f57600080fd5b61133b89828a0161114e565b935050608087013567ffffffffffffffff81111561135857600080fd5b61136489828a016111ba565b92505060a087013567ffffffffffffffff81111561138157600080fd5b61138d89828a0161114e565b9150509295509295509295565b6000602082840312156113ac57600080fd5b60006104918484611279565b6113c181611679565b82525050565b60006113d282611666565b6113dc8185611670565b93506113ec81856020860161168d565b6113f5816116bd565b9093019392505050565b600061140c600683611670565b651c995cdd5b1d60d21b815260200192915050565b600061142e600883611670565b6778702e627974657360c01b815260200192915050565b6000611452601b83611670565b7f76616c2e6c656e67746820253332203d3d2030206661696c65642e0000000000815260200192915050565b600061148b600a83611670565b69617870632e627974657360b01b815260200192915050565b60006114b1600983611670565b686178702e627974657360b81b815260200192915050565b60006114d6600a83611670565b697276616c2e627974657360b01b815260200192915050565b60006114fc601a83611670565b7f6578706f6e656e742e6e65673d3d66616c7365206661696c6564000000000000815260200192915050565b6113c18161167e565b6020808252810161046f81846113c7565b60408082528101611552816113ff565b905061065660208301846113b8565b6060808252810161157181611421565b9050818103602083015261158581856113c7565b905061046f6040830184611528565b6020808252810161065681611445565b606080825281016115718161147e565b60608082528101611571816114a4565b60608082528101611571816114c9565b60208082528101610656816114ef565b602081016106568284611528565b6000604051905081810181811067ffffffffffffffff8211171561161557600080fd5b604052919050565b600067ffffffffffffffff82111561163457600080fd5b5060209081020190565b600067ffffffffffffffff82111561165557600080fd5b506020601f91909101601f19160190565b6000815192915050565b90815260200190565b151590565b90565b82818337506000910152565b60005b838110156116a8578082015183820152602001611690565b838111156116b7576000848401525b50505050565b601f01601f191690565b6116d08161167e565b81146116db57600080fd5b5056fe6def907f4bd22c527ea908da93d23b5568c87c1c1dbc19feeab37da2102957cca365627a7a7231582041c1be52902ff27a026916da55b11b40bab5fa387488c82b153581b24d104b846c6578706572696d656e74616cf564736f6c63430005110040";

    public static final String FUNC_DEP = "dep";

    public static final String FUNC_FEED_PROOF_PATH = "feed_proof_path";

    public static final String FUNC_REV = "rev";

    public static final String FUNC_TOBYTES = "toBytes";

    public static final Event DEBUG_EVENT = new Event("Debug",
                                                      Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                                      }, new TypeReference<DynamicBytes>() {
                                                      }, new TypeReference<Uint256>() {
                                                      }));
    ;

    public static final Event DEBUGBOOL_EVENT = new Event("DebugBool",
                                                          Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                                          }, new TypeReference<Bool>() {
                                                          }));
    ;

    @Deprecated
    protected TestGas(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TestGas(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TestGas(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TestGas(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DebugEventResponse> getDebugEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUG_EVENT, transactionReceipt);
        ArrayList<DebugEventResponse> responses = new ArrayList<DebugEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugEventResponse typedResponse = new DebugEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.val = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.bitlen = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugEventResponse> debugEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DebugEventResponse>() {
            @Override
            public DebugEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUG_EVENT, log);
                DebugEventResponse typedResponse = new DebugEventResponse();
                typedResponse.log = log;
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.val = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.bitlen = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugEventResponse> debugEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUG_EVENT));
        return debugEventFlowable(filter);
    }

    public List<DebugBoolEventResponse> getDebugBoolEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUGBOOL_EVENT, transactionReceipt);
        ArrayList<DebugBoolEventResponse> responses = new ArrayList<DebugBoolEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugBoolEventResponse typedResponse = new DebugBoolEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.val = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugBoolEventResponse> debugBoolEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DebugBoolEventResponse>() {
            @Override
            public DebugBoolEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUGBOOL_EVENT, log);
                DebugBoolEventResponse typedResponse = new DebugBoolEventResponse();
                typedResponse.log = log;
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.val = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugBoolEventResponse> debugBoolEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUGBOOL_EVENT));
        return debugBoolEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> dep() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEP,
                                                                                               Arrays.<Type>asList(),
                                                                                               Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                                                                                               }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> feed_proof_path(List<byte[]> _vals, List<byte[]> _avals, List<byte[]> _cvals, List<byte[]> _x, List<BigInteger> _p, List<byte[]> _modulus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FEED_PROOF_PATH,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                                            org.web3j.abi.datatypes.DynamicBytes.class,
                                            org.web3j.abi.Utils.typeMap(_vals, org.web3j.abi.datatypes.DynamicBytes.class)),
                                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                                            org.web3j.abi.datatypes.DynamicBytes.class,
                                            org.web3j.abi.Utils.typeMap(_avals, org.web3j.abi.datatypes.DynamicBytes.class)),
                                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                                            org.web3j.abi.datatypes.DynamicBytes.class,
                                            org.web3j.abi.Utils.typeMap(_cvals, org.web3j.abi.datatypes.DynamicBytes.class)),
                                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                                            org.web3j.abi.datatypes.DynamicBytes.class,
                                            org.web3j.abi.Utils.typeMap(_x, org.web3j.abi.datatypes.DynamicBytes.class)),
                                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Int256>(
                                            org.web3j.abi.datatypes.generated.Int256.class,
                                            org.web3j.abi.Utils.typeMap(_p, org.web3j.abi.datatypes.generated.Int256.class)),
                                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                                            org.web3j.abi.datatypes.DynamicBytes.class,
                                            org.web3j.abi.Utils.typeMap(_modulus, org.web3j.abi.datatypes.DynamicBytes.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> rev() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REV,
                                                                                               Arrays.<Type>asList(),
                                                                                               Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                                                                                               }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> toBytes(BigInteger x) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOBYTES,
                                                                                               Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(x)),
                                                                                               Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {
                                                                                               }));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static TestGas load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestGas(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TestGas load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestGas(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TestGas load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TestGas(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TestGas load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TestGas(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TestGas> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(TestGas.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<TestGas> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(TestGas.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<TestGas> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(TestGas.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<TestGas> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(TestGas.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static class DebugEventResponse extends BaseEventResponse {
        public String msg;

        public byte[] val;

        public BigInteger bitlen;
    }

    public static class DebugBoolEventResponse extends BaseEventResponse {
        public String msg;

        public Boolean val;
    }
}
