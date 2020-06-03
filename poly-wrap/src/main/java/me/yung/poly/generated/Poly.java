package me.yung.poly.generated;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
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
 * <p>Generated with web3j version 4.6.0-SNAPSHOT.
 */
@SuppressWarnings("rawtypes")
public class Poly extends Contract {
    public static final String BINARY = "60806040526040516200204d3803806200204d8339810180604052620000299190810190620001a0565b600080546001600160a01b031916331790558451620000509060019060208801906200008a565b508351620000669060029060208701906200008a565b50346003556004929092556008556009555050600a805461ffff19169055620002d1565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000cd57805160ff1916838001178555620000fd565b82800160010185558215620000fd579182015b82811115620000fd578251825591602001919060010190620000e0565b506200010b9291506200010f565b5090565b6200012c91905b808211156200010b576000815560010162000116565b90565b600082601f8301126200014157600080fd5b815162000158620001528262000276565b6200024f565b915080825260208301602083018583830111156200017557600080fd5b620001828382846200029e565b50505092915050565b60006200019982516200012c565b9392505050565b600080600080600060a08688031215620001b957600080fd5b85516001600160401b03811115620001d057600080fd5b620001de888289016200012f565b95505060208601516001600160401b03811115620001fb57600080fd5b62000209888289016200012f565b94505060406200021c888289016200018b565b93505060606200022f888289016200018b565b925050608062000242888289016200018b565b9150509295509295909350565b6040518181016001600160401b03811182821017156200026e57600080fd5b604052919050565b60006001600160401b038211156200028d57600080fd5b506020601f91909101601f19160190565b60005b83811015620002bb578181015183820152602001620002a1565b83811115620002cb576000848401525b50505050565b611d6c80620002e16000396000f3fe6080604052600436106100915760003560e01c8063a415529511610059578063a41552951461013a578063aeee48c01461014d578063bc25a54314610162578063dad6924a14610177578063eede166c1461018c57610091565b80630ca5fc25146100965780635b6a589f146100b857806369ca1b36146100d8578063855a0fe3146101035780639b9bb97814610125575b600080fd5b3480156100a257600080fd5b506100b66100b13660046118d1565b6101a1565b005b3480156100c457600080fd5b506100b66100d33660046117e5565b610219565b3480156100e457600080fd5b506100ed610430565b6040516100fa9190611bba565b60405180910390f35b34801561010f57600080fd5b506101186104ce565b6040516100fa9190611c40565b34801561013157600080fd5b506100ed6104d4565b6100b661014836600461190e565b61053f565b34801561015957600080fd5b506101186105c6565b34801561016e57600080fd5b506100b66105cc565b34801561018357600080fd5b506100ed610745565b34801561019857600080fd5b506100ed6107b0565b6009544211156101cf57604051600160e51b62461bcd0281526004016101c690611c30565b60405180910390fd5b600a805460ff191660011790557fa0bd38df1b31aad538444bd62f83ed49738e0a8cbe27e77a60110c30ddf787ba60078260405161020e929190611bcb565b60405180910390a150565b7f7cdb51e9dbbc205231228146c3246e7f914aa6d4a33170e43ecc8e3593481d1a60405161024690611bf0565b60405180910390a16060855160405190808252806020026020018201604052801561028b57816020015b610278611538565b8152602001906001900390816102705790505b50905060005b8651811015610377576102b98782815181106102a957fe5b602002602001015160008061081b565b8282815181106102c557fe5b6020026020010151526102dd8682815181106102a957fe5b8282815181106102e957fe5b6020026020010151602001526103048582815181106102a957fe5b82828151811061031057fe5b60200260200101516040015261032b8482815181106102a957fe5b82828151811061033757fe5b60200260200101516060015282818151811061034f57fe5b602002602001015182828151811061036357fe5b602002602001015160800152600101610291565b507f7cdb51e9dbbc205231228146c3246e7f914aa6d4a33170e43ecc8e3593481d1a6040516103a590611c00565b60405180910390a16103b681610882565b6103d557604051600160e51b62461bcd0281526004016101c690611c10565b6005546004546003546001600160a01b03909216910180156108fc0290604051600060405180830381858888f19350505050158015610418573d6000803e3d6000fd5b5050600a805461ff0019166101001790555050505050565b60068054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104c65780601f1061049b576101008083540402835291602001916104c6565b820191906000526020600020905b8154815290600101906020018083116104a957829003601f168201915b505050505081565b60045481565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104c65780601f1061049b576101008083540402835291602001916104c6565b60085442111561056457604051600160e51b62461bcd0281526004016101c690611c30565b600454341461058857604051600160e51b62461bcd0281526004016101c690611c20565b600681805161059b929160200190611580565b5060078280516105af929160200190611580565b5050600580546001600160a01b0319163317905550565b60035481565b600a54610100900460ff16156105e157610743565b600854421180156105fb57506005546001600160a01b0316155b15610654576000546003546001600160a01b039091169080156108fc0290604051600060405180830381858888f1935050505015801561063f573d6000803e3d6000fd5b50600a805461ff001916610100179055610743565b60095442111561074357600a5460ff16156106ee576000546003546001600160a01b039091169080156108fc0290604051600060405180830381858888f193505050501580156106a8573d6000803e3d6000fd5b506005546004546001600160a01b039091169080156108fc0290604051600060405180830381858888f193505050501580156106e8573d6000803e3d6000fd5b50610733565b6005546004546003546001600160a01b03909216910180156108fc0290604051600060405180830381858888f19350505050158015610731573d6000803e3d6000fd5b505b600a805461ff0019166101001790555b565b60078054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104c65780601f1061049b576101008083540402835291602001916104c6565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104c65780601f1061049b576101008083540402835291602001916104c6565b6108236115fe565b602084518161082e57fe5b061561083957600080fd5b8161084657838152610865565b606060208551016040518181838960046101c2fa509081016040528252505b8215156020820152610876846108cc565b60408201529392505050565b6000805b82518110156108c1576108ab83828151811061089e57fe5b60200260200101516108ef565b6108b95760009150506108c7565b600101610886565b50600190505b919050565b6000806020830151905060208351036008026108e782610957565b019392505050565b60006108f96115fe565b8260600151905060015b83608001518112156109275761091d828560600151610b48565b9150600101610903565b5061094f835161094861093e848760200151610b48565b8660400151610ba5565b6000610d68565b159392505050565b600160801b680100000000000000006401000000006201000061010060106004600260001989019081041790810417908104179081041790810417908104179081041790810417600101906000906040517ff8f9cbfae6cc78fbefe7cdc3a1793dfcf4f0e8bbd8cec470b6a28a7a5a3e1efd81527ff5ecf1b3e9debc68e1d9cfabc5997135bfb7a7a3938b7b606b5b4b3f2f1f0ffe60208201527ff6e4ed9ff2d6b458eadcdf97bd91692de2d4da8fd2d0ac50c6ae9a827252361660408201527fc8c0b887b0a8a4489c948c7f847c6125746c645c544c444038302820181008ff60608201527ff7cae577eec2a03cf3bad76fb589591debb2dd67e0aa9834bea6925f6a4a2e0e60808201527fe39ed557db96902cd38ed14fad815115c786af479b7e8324736353433727170760a08201527fc976c13bb96e881cb166a933a55e490d9d56952b8d4e801485467d236242260660c08201527f753a6d1b65325d0c552a4d1345224105391a310b29122104190a11030902010060e082015261010081016040527e818283848586878898a8b8c8d8e8f929395969799a9b9d9e9faaeb6bedeeff600160f81b8082870204818160ff0385015104600160ff1b861161010002019550505050600019820182161590508015610b3657508215155b15610b42578160010191505b50919050565b610b506115fe565b610b5c83836000610e8d565b9050610b6a83836001610d68565b15610b9357610b776115fe565b610b8384846001610e8d565b9050610b8f8282610f67565b9150505b610b9e8160026110ef565b9392505050565b610bad6115fe565b610bb56115fe565b60405180606001604052806040516040808201815260208083526000818401819052928452830182905291820152909150840151158015610bf857508260400151155b15610c04579050610d62565b8360400151610c165782915050610d62565b8260400151610c285783915050610d62565b6060600080610c3987876000610d68565b9050866020015180610c4c575085602001515b15610d155786602001518015610c63575085602001515b15610cae5760008112610c8b57610c818751875189604001516111a6565b9093509150610ca2565b610c9c8651885188604001516111a6565b90935091505b60016020860152610d10565b8060011415610cd957610cc3875187516112d5565b9093509150602087015115156020860152610d10565b806000191415610d0457610cef865188516112d5565b90935091506020870151156020860152610d10565b83945050505050610d62565b610d57565b60008112610d3857610d2e8751875189604001516111a6565b9093509150610d4f565b610d498651885188604001516111a6565b90935091505b600060208601525b509083526040830152505b92915050565b600060018215610de15784602001518015610d84575083602001515b15610d925750600019610de1565b8460200151158015610daa5750836020015115156001145b15610db9576001915050610b9e565b846020015115156001148015610dd157508360200151155b15610de157600019915050610b9e565b836040015185604001511115610df8579050610b9e565b846040015184604001511115610e12576000039050610b9e565b600080808080895151905060208a510194506020895101935060005b81811015610e7c5780860151935080850151925082841115610e595750949550610b9e945050505050565b83831115610e74578660001902975050505050505050610b9e565b602001610e2e565b5060009a9950505050505050505050565b610e956115fe565b610e9d6115fe565b6040518060600160405280604051604080820181526020808352600281840181905292845260009084018190529201529091508060608515610ee857610ee38888610f67565b610ef2565b610ef28888610ba5565b945060008560400151600281029450600160ff86161b935090506040516040818101905260206001610100870401810282528082018590529092508251018201604052610f3d6115fe565b8281526000602082015260408101859052610f598787836113d0565b9a9950505050505050505050565b610f6f6115fe565b610f776115fe565b604051806060016040528060405180604001604052806020815260200160008152508152602001600015158152602001600081525090506060600080610fbf87876000610d68565b9150866020015180610fd2575085602001515b156110965786602001518015610fe9575085602001515b1561103c57816001141561101457611003875187516112d5565b600160208801529093509050611037565b816000191415610d045761102a865188516112d5565b6000602088015290935090505b611091565b6000821261105f576110558751875189604001516111a6565b9093509050611076565b6110708651885188604001516111a6565b90935090505b8660200151611086576000611089565b60015b151560208601525b6110df565b81600114156110bc576110ab875187516112d5565b6000602088015290935090506110df565b816000191415610d04576110d2865188516112d5565b6001602088015290935090505b9184525060408301525092915050565b6110f76115fe565b6060600061010084900381808080895151601f1992509050808a51019250601f1981015b8281101561115d578351965080156001811461113d5760208503519550611142565b600095505b5093851b95891c86811784529593601f19938401930161111b565b506020830192505b60008351141561117f5760209290920191601f1901611165565b601f198301818152808b5296508860408b01510360408b0152509798975050505050505050565b60606000606059600019600088518901885189018a5160208601018b515b80156112665783518c518e51038211600181146112045786820184526001871482891416600181146111f957600097506111fe565b600197505b50611253565b8451878184010185528781038903831160018114611245576000821160008a11178a8514166001811461123a576000995061123f565b600199505b5061124a565b600198505b50602086039550505b5050601f199384019391820191016111c4565b5083156001811461127a5760018252611281565b6020870196505b50859650836020028c5101875260208751018701604052505050505050600060208201519050610100850681901c600114806112bd5750806001145b156112c9578460010194505b50959294509192505050565b6060600081815960001987518751808203828b01828b0184870160208101865b801561137b57845186821160018114611332578c8203855260018d146000831416600181146113275760009d5061132c565b60019d505b50611368565b85518d81840303865260018e148c8214168e82018410176001811461135a5760009e5061135f565b60019e505b50602087039650505b5050601f199485019492830192016112f5565b506020820191505b6000825114156113a457602089019850602087039650602082019150611383565b889a50868b528060405250505050505050505060006113c2836108cc565b929792965091945050505050565b6113d86115fe565b8260200151156113e757600080fd5b60606113f7855185518551611469565b90506000602082015190506001602083518161140f57fe5b04036101000261141e82610957565b83855201905060208601511580611447575085602001518015611447575061144585611524565b155b611452576001611455565b60005b151560208401526040830152509392505050565b606083518351835160405183815282602082015281604082015283606082018560208b0160046101c2fa84606001848184018660208c0160046101c2fa91508401838382018160208b0160046101c2fa91508180156114c7576114c9565bfe5b5083018360608401828560056105465a03fa91508180156114c7575083836060015b60016000825114141561150757601f19909101906020016114eb565b601f19019081529290930160600160405250979650505050505050565b600081515182510160028151069392505050565b604051806101a0016040528061154c6115fe565b81526020016115596115fe565b81526020016115666115fe565b81526020016115736115fe565b8152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106115c157805160ff19168380011785556115ee565b828001600101855582156115ee579182015b828111156115ee5782518255916020019190600101906115d3565b506115fa929150611621565b5090565b604051806060016040528060608152602001600015158152602001600081525090565b61163b91905b808211156115fa5760008155600101611627565b90565b600082601f83011261164f57600080fd5b813561166261165d82611c79565b611c4e565b81815260209384019390925082018360005b838110156116a0578135860161168a888261178a565b8452506020928301929190910190600101611674565b5050505092915050565b600082601f8301126116bb57600080fd5b81356116c961165d82611c79565b915081818352602084019350602081019050838560208402820111156116ee57600080fd5b60005b838110156116a0578161170488826117d9565b84525060209283019291909101906001016116f1565b600082601f83011261172b57600080fd5b813561173961165d82611c79565b9150818183526020840193506020810190508385602084028201111561175e57600080fd5b60005b838110156116a0578161177488826117d9565b8452506020928301929190910190600101611761565b600082601f83011261179b57600080fd5b81356117a961165d82611c9a565b915080825260208301602083018583830111156117c557600080fd5b6117d0838284611cec565b50505092915050565b6000610b9e823561163b565b600080600080600060a086880312156117fd57600080fd5b853567ffffffffffffffff81111561181457600080fd5b6118208882890161163e565b955050602086013567ffffffffffffffff81111561183d57600080fd5b6118498882890161163e565b945050604086013567ffffffffffffffff81111561186657600080fd5b6118728882890161163e565b935050606086013567ffffffffffffffff81111561188f57600080fd5b61189b8882890161163e565b925050608086013567ffffffffffffffff8111156118b857600080fd5b6118c4888289016116aa565b9150509295509295909350565b6000602082840312156118e357600080fd5b813567ffffffffffffffff8111156118fa57600080fd5b6119068482850161171a565b949350505050565b6000806040838503121561192157600080fd5b823567ffffffffffffffff81111561193857600080fd5b6119448582860161178a565b925050602083013567ffffffffffffffff81111561196157600080fd5b61196d8582860161178a565b9150509250929050565b60006119838383611bab565b505060200190565b600061199682611cd9565b6119a08185611ce3565b93506119ab83611cc2565b60005b828110156119d6576119c1868351611977565b95506119cc82611cc2565b91506001016119ae565b5093949350505050565b60006119eb82611cd9565b6119f58185611ce3565b9350611a05818560208601611cf8565b611a0e81611d28565b9093019392505050565b600081546001811660008114611a355760018114611a5b57611a9a565b607f6002830416611a468187611ce3565b60ff1984168152955050602085019250611a9a565b60028204611a698187611ce3565b9550611a7485611cc8565b60005b82811015611a9357815488820152600190910190602001611a77565b8701945050505b505092915050565b6000611aaf600683611ce3565b600160d01b656265666f726502815260200192915050565b6000611ad4601683611ce3565b7f6166746572206275696c64207061746820617272617900000000000000000000815260200192915050565b6000611b0d601383611ce3565b7f766572696669636174696f6e206661696c656400000000000000000000000000815260200192915050565b6000611b46600f83611ce3565b7f696e76616c6964206465706f7369740000000000000000000000000000000000815260200192915050565b6000611b7f600c83611ce3565b7f696e76616c69642074696d650000000000000000000000000000000000000000815260200192915050565b611bb48161163b565b82525050565b60208082528101610b9e81846119e0565b60408082528101611bdc8185611a18565b90508181036020830152611906818461198b565b60208082528101610d6281611aa2565b60208082528101610d6281611ac7565b60208082528101610d6281611b00565b60208082528101610d6281611b39565b60208082528101610d6281611b72565b60208101610d628284611bab565b6000604051905081810181811067ffffffffffffffff82111715611c7157600080fd5b604052919050565b600067ffffffffffffffff821115611c9057600080fd5b5060209081020190565b600067ffffffffffffffff821115611cb157600080fd5b506020601f91909101601f19160190565b60200190565b600081600052602060002092915050565b6000815192915050565b90815260200190565b82818337506000910152565b60005b83811015611d13578082015183820152602001611cfb565b83811115611d22576000848401525b50505050565b601f01601f19169056fea265627a7a723058204335deade7899d38c4b9b84ce72b42dfdda1c0fc369bedf074d229f6cf363d686c6578706572696d656e74616cf50037";

    public static final String FUNC_VERIFY = "verify";

    public static final String FUNC_FEED_PROOF_PATH = "feed_proof_path";

    public static final String FUNC_RESULT_PROOF_ROOT = "result_proof_root";

    public static final String FUNC_DEP = "dep";

    public static final String FUNC_A_ADDR = "a_addr";

    public static final String FUNC_SUBMIT_RESULT = "submit_result";

    public static final String FUNC_REV = "rev";

    public static final String FUNC_CLAIM_TOKENS = "claim_tokens";

    public static final String FUNC_RESULT_ADDR = "result_addr";

    public static final String FUNC_X_ADDR = "x_addr";

    public static final Event GETPATH_EVENT = new Event("GetPath",
                                                        Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                                        }, new TypeReference<DynamicArray<Uint256>>() {
                                                        }));
    ;

    public static final Event DEBUG_EVENT = new Event("Debug",
                                                      Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                                      }));
    ;

    @Deprecated
    protected Poly(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Poly(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Poly(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Poly(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> verify(List<BigInteger> _path_spec) {
        final Function function = new Function(
                FUNC_VERIFY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_path_spec, org.web3j.abi.datatypes.generated.Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> feed_proof_path(List<byte[]> _vals, List<byte[]> _avals, List<byte[]> _cvals, List<byte[]> _x, List<BigInteger> _p) {
        final Function function = new Function(
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
                                            org.web3j.abi.Utils.typeMap(_p, org.web3j.abi.datatypes.generated.Int256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> result_proof_root() {
        final Function function = new Function(FUNC_RESULT_PROOF_ROOT,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> dep() {
        final Function function = new Function(FUNC_DEP,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> a_addr() {
        final Function function = new Function(FUNC_A_ADDR,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> submit_result(String _result_addr, String _result_proof_root, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SUBMIT_RESULT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_result_addr),
                                    new org.web3j.abi.datatypes.Utf8String(_result_proof_root)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> rev() {
        final Function function = new Function(FUNC_REV,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> claim_tokens() {
        final Function function = new Function(
                FUNC_CLAIM_TOKENS,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> result_addr() {
        final Function function = new Function(FUNC_RESULT_ADDR,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> x_addr() {
        final Function function = new Function(FUNC_X_ADDR,
                                               Arrays.<Type>asList(),
                                               Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                                               }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<GetPathEventResponse> getGetPathEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GETPATH_EVENT, transactionReceipt);
        ArrayList<GetPathEventResponse> responses = new ArrayList<GetPathEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GetPathEventResponse typedResponse = new GetPathEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.result_addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._path_spec = (List<Uint256>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GetPathEventResponse> getPathEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, GetPathEventResponse>() {
            @Override
            public GetPathEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GETPATH_EVENT, log);
                GetPathEventResponse typedResponse = new GetPathEventResponse();
                typedResponse.log = log;
                typedResponse.result_addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._path_spec = (List<Uint256>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GetPathEventResponse> getPathEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GETPATH_EVENT));
        return getPathEventFlowable(filter);
    }

    public List<DebugEventResponse> getDebugEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUG_EVENT, transactionReceipt);
        ArrayList<DebugEventResponse> responses = new ArrayList<DebugEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugEventResponse typedResponse = new DebugEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugEventResponse> debugEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DebugEventResponse>() {
            @Override
            public DebugEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUG_EVENT, log);
                DebugEventResponse typedResponse = new DebugEventResponse();
                typedResponse.log = log;
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugEventResponse> debugEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUG_EVENT));
        return debugEventFlowable(filter);
    }

    @Deprecated
    public static Poly load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Poly(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Poly load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Poly(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Poly load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Poly(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Poly load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Poly(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Poly> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _a_addr, String _x_addr, BigInteger _dep, BigInteger _t1, BigInteger _t2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_a_addr),
                                                                                          new org.web3j.abi.datatypes.Utf8String(_x_addr),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_dep),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t1),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t2)));
        return deployRemoteCall(Poly.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Poly> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _a_addr, String _x_addr, BigInteger _dep, BigInteger _t1, BigInteger _t2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_a_addr),
                                                                                          new org.web3j.abi.datatypes.Utf8String(_x_addr),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_dep),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t1),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t2)));
        return deployRemoteCall(Poly.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Poly> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _a_addr, String _x_addr, BigInteger _dep, BigInteger _t1, BigInteger _t2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_a_addr),
                                                                                          new org.web3j.abi.datatypes.Utf8String(_x_addr),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_dep),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t1),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t2)));
        return deployRemoteCall(Poly.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Poly> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _a_addr, String _x_addr, BigInteger _dep, BigInteger _t1, BigInteger _t2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_a_addr),
                                                                                          new org.web3j.abi.datatypes.Utf8String(_x_addr),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_dep),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t1),
                                                                                          new org.web3j.abi.datatypes.generated.Uint256(_t2)));
        return deployRemoteCall(Poly.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static class GetPathEventResponse extends BaseEventResponse {
        public String result_addr;

        public List<Uint256> _path_spec;
    }

    public static class DebugEventResponse extends BaseEventResponse {
        public String msg;
    }
}
