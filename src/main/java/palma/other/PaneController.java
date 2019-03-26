package palma.other;

public abstract class PaneController {

    private StageHost stageHost;
    private PaneHost paneHost;

    protected PaneController(){}

    public StageHost getStageHost() {
        return stageHost;
    }

    public PaneHost getPaneHost() {
        return paneHost;
    }

    public void setHosts(StageHost stageHost, PaneHost paneHost) {
        this.stageHost = stageHost;
        this.paneHost = paneHost;
    }

    public void open(Object ... bundle){}

    public void init(){}
}
