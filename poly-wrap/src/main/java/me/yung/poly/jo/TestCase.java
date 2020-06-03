package me.yung.poly.jo;

public class TestCase {
    private final int x;
    private final int blMod;
    private Type type;
    private String msg;
    private int degree;
    private int numTerms;
    // times
    private long t0;
    private long buildTask;
    private long publishTaskIPFS;
    private long publishTaskETH;
    private long workerPullTask;
    private long workerProcessTask;
    private long workerSubmitResultIPFS;
    private long workerSubmitResultETH;
    private long userTriggerVerify;
    private long userVerify;
    private long oracleVerify;
    private long invokeVerification;
    private long gasUsage;
    public TestCase(Type type, String msg, int x, int degree, int numTerms, int blMod) {
        this.type = type;
        this.msg = msg;
        this.x = x;
        this.degree = degree;
        this.numTerms = numTerms;
        this.blMod = blMod;
    }

    public int getBlMod() {
        return blMod;
    }

    public void setInvokeVerification(long invokeVerification) {
        this.invokeVerification = invokeVerification;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.join(",",
                           new String[]{
                                   String.valueOf(type),
                                   String.valueOf(msg),
                                   String.valueOf(x),
                                   String.valueOf(degree),
                                   String.valueOf(numTerms),
                                   String.valueOf(blMod),
                                   String.valueOf(t0),
                                   String.valueOf(buildTask),
                                   String.valueOf(publishTaskIPFS),
                                   String.valueOf(publishTaskETH),
                                   String.valueOf(workerPullTask),
                                   String.valueOf(workerProcessTask),
                                   String.valueOf(workerSubmitResultIPFS),
                                   String.valueOf(workerSubmitResultETH),
                                   String.valueOf(userTriggerVerify),
                                   String.valueOf(userVerify),
                                   String.valueOf(oracleVerify),
                                   String.valueOf(invokeVerification),
                                   String.valueOf(gasUsage),
                           });
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setWorkerSubmitResultIPFS(long workerSubmitResultIPFS) {
        this.workerSubmitResultIPFS = workerSubmitResultIPFS;
    }

    public void setWorkerSubmitResultETH(long workerSubmitResultETH) {
        this.workerSubmitResultETH = workerSubmitResultETH;
    }

    public void setWorkerPullTask(long workerPullTask) {
        this.workerPullTask = workerPullTask;
    }

    public void setWorkerProcessTask(long workerProcessTask) {
        this.workerProcessTask = workerProcessTask;
    }

    public void setT0(long t0) {
        this.t0 = t0;
    }

    public void setBuildTask(long buildTask) {
        this.buildTask = buildTask;
    }

    public void setPublishTaskIPFS(long publishTaskIPFS) {
        this.publishTaskIPFS = publishTaskIPFS;
    }

    public void setPublishTaskETH(long publishTaskETH) {
        this.publishTaskETH = publishTaskETH;
    }

    public void setUserTriggerVerify(long userTriggerVerify) {
        this.userTriggerVerify = userTriggerVerify;
    }

    public void setUserVerify(long userVerify) {
        this.userVerify = userVerify;
    }

    public void setOracleVerify(long oracleVerify) {
        this.oracleVerify = oracleVerify;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getNumTerms() {
        return numTerms;
    }

    public void setNumTerms(int numTerms) {
        this.numTerms = numTerms;
    }

    public int getX() {
        return x;
    }

    public long getGasUsage() {
        return gasUsage;
    }

    public void setGasUsage(long gasUsage) {
        this.gasUsage = gasUsage;
    }

    public enum Type {
        NORMAL, SPARSE, MULTIVAR;
    }
}
