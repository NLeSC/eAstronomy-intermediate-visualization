package lofar;

import lofar.dataFormats.DataProvider;

public class AntennaBandpass {
    float lbaFirst = 9.9609375f; // freq of first element is: 9.9609375 MHz
    float lbaLast = 83.3984375f; // freq of last element is: 83.3984375 MHz
    float lbaStep = 0.1953125f; // frequency step = .1953125 MHz
    float[] lba = { 0.0160284712f, 0.0163214637f, 0.0164903444f, 0.021474589f, 0.0233614541f, 0.0250446878f, 0.0301580575f,
            0.0315418227f, 0.0315418227f, 0.0322652893f, 0.0340736725f, 0.036027132f, 0.036027132f, 0.0380132511f, 0.040300412f,
            0.0463506116f, 0.048843359f, 0.050043678f, 0.0501125986f, 0.0509513796f, 0.0563494399f, 0.0612408996f, 0.0615247875f,
            0.0639160652f, 0.066216596f, 0.0703592975f, 0.0722836603f, 0.0724427778f, 0.0745968922f, 0.0779724683f,
            0.0785233269f, 0.0808605499f, 0.0819935135f, 0.0850267758f, 0.0872223921f, 0.0900575958f, 0.0908830899f,
            0.092274325f, 0.097532826f, 0.0979401817f, 0.1006294033f, 0.1009435133f, 0.1032533797f, 0.1067705186f, 0.1088657593f,
            0.1130309206f, 0.1170320955f, 0.1171347963f, 0.1214266847f, 0.1244203008f, 0.1251617478f, 0.1258624638f,
            0.1274457174f, 0.1308291519f, 0.1314126911f, 0.1332769853f, 0.13578445f, 0.1395930333f, 0.1447835685f, 0.1456322317f,
            0.1471385364f, 0.1488595149f, 0.1545079759f, 0.1566623423f, 0.1583720175f, 0.1583720175f, 0.1641845276f,
            0.1666167084f, 0.1710545211f, 0.1711923571f, 0.1745017078f, 0.1788423348f, 0.1823144259f, 0.1846508344f,
            0.1889396276f, 0.1922522678f, 0.1932393221f, 0.1968558526f, 0.1982246294f, 0.2020617318f, 0.2087900868f,
            0.2129107474f, 0.2162360445f, 0.2162360445f, 0.2179292224f, 0.2212280348f, 0.2255665718f, 0.2394607422f,
            0.241504924f, 0.241504924f, 0.2431223987f, 0.2458635723f, 0.2489055224f, 0.2493456295f, 0.2493456295f, 0.2591349663f,
            0.2623738056f, 0.2647449292f, 0.2694522975f, 0.2743871712f, 0.2764938098f, 0.2867573126f, 0.2909484118f,
            0.2922886479f, 0.3050264494f, 0.3059949846f, 0.3138625462f, 0.3146878145f, 0.3326637497f, 0.3357295838f,
            0.3454929793f, 0.3456164754f, 0.35433026f, 0.3570469588f, 0.3673789707f, 0.3707657327f, 0.3784309284f, 0.3801252961f,
            0.3942148772f, 0.4078480239f, 0.4249313059f, 0.4300211233f, 0.4473348862f, 0.4630394417f, 0.4853231085f,
            0.4853231085f, 0.5009465813f, 0.5118228047f, 0.5331047804f, 0.5570400345f, 0.5873308522f, 0.5873308522f,
            0.5968435896f, 0.6065641401f, 0.6317292336f, 0.6414863843f, 0.661621959f, 0.6877361234f, 0.7077270834f,
            0.7143393053f, 0.7488769356f, 0.7762789514f, 0.8035904554f, 0.8249981272f, 0.839979912f, 0.8765737749f,
            0.9299107318f, 0.9341480251f, 0.9774335962f, 0.9774335962f, 0.9996456762f, 1f, 1f, 1f, 0.9904906839f, 0.985089802f,
            0.96636397f, 0.9234254266f, 0.9123309466f, 0.8638473357f, 0.8429922217f, 0.7962989642f, 0.7459368375f, 0.7357669163f,
            0.6922140427f, 0.6501555965f, 0.6096393773f, 0.6079585574f, 0.597088952f, 0.5866750923f, 0.5851850624f,
            0.5584937626f, 0.5584937626f, 0.5424172336f, 0.5220827037f, 0.4559161965f, 0.4488081202f, 0.4444429816f,
            0.4212181975f, 0.4063724829f, 0.3873758144f, 0.3653590537f, 0.3580085247f, 0.339614352f, 0.3298859704f,
            0.3104159657f, 0.3104159657f, 0.2991900819f, 0.2839889236f, 0.2637807278f, 0.2600225268f, 0.2359617226f,
            0.2359617226f, 0.2320969944f, 0.2165266009f, 0.2021823741f, 0.2018672545f, 0.1874662846f, 0.1749343114f,
            0.1723024282f, 0.1628602255f, 0.1605209106f, 0.1489213933f, 0.1481553198f, 0.138263049f, 0.1342270581f,
            0.1328363043f, 0.1209716329f, 0.1165797023f, 0.1124157117f, 0.1124157117f, 0.1072927941f, 0.103954002f,
            0.0901346742f, 0.0826360078f, 0.0820355826f, 0.0820355826f, 0.0732111867f, 0.0651125834f, 0.056478441f,
            0.0468424239f, 0.0450064724f, 0.0431955898f, 0.0361905606f, 0.0308610519f, 0.0253054699f, 0.0175955056f,
            0.0171112812f, 0.0103167161f, 0.0094603469f, 0.0062798496f, 0.0059335646f, 0.0059335646f };

    float hbaLowFirst = 119.7265625f; // freq of first element is: 119.7265625 MHz
    float hbaLowLast = 166.2109375f; // freq of last element is:  166.2109375 MHz
    float hbaLowStep = .1953125f; // frequency step = .1953125 MHz
    float[] hbaLow = { 0.0216738281f, 0.0216738281f, 0.0223452207f, 0.0223452207f, 0.0223452207f, 0.0223878601f, 0.0223878601f,
            0.022971734f, 0.0232150568f, 0.023287806f, 0.023372569f, 0.023372569f, 0.023372569f, 0.0236831216f, 0.0238087598f,
            0.0238804321f, 0.0239160075f, 0.0239160075f, 0.0239160075f, 0.0240318762f, 0.0240781195f, 0.0241844953f,
            0.0241939766f, 0.0244489359f, 0.0244538388f, 0.0244838258f, 0.024412517f, 0.024412517f, 0.024412517f, 0.0245361991f,
            0.0245789303f, 0.0246346804f, 0.0246440295f, 0.0247871808f, 0.0247871808f, 0.0250331571f, 0.0249987626f,
            0.0251310593f, 0.0250975746f, 0.0250186358f, 0.0248397673f, 0.0247849547f, 0.0247849547f, 0.0247912409f,
            0.0247999737f, 0.0247912409f, 0.0247777792f, 0.0243707757f, 0.0243707757f, 0.0243707757f, 0.0244050633f,
            0.0244050633f, 0.0246012042f, 0.0246012042f, 0.0246012042f, 0.0248560611f, 0.0248560611f, 0.0248560611f,
            0.0247376113f, 0.0247200722f, 0.024607426f, 0.0244658498f, 0.0247200722f, 0.0247200722f, 0.0247987564f,
            0.0247987564f, 0.0247987564f, 0.0245996612f, 0.0242001458f, 0.0242001458f, 0.0242001458f, 0.0241937607f,
            0.0242333039f, 0.0244519747f, 0.0243883665f, 0.0243883665f, 0.0243883665f, 0.0243883665f, 0.0243037095f,
            0.0243037095f, 0.0242675787f, 0.0242091496f, 0.0242091496f, 0.0238807441f, 0.0237324423f, 0.0237324423f,
            0.0238600374f, 0.0238253301f, 0.0238253301f, 0.0238600374f, 0.0238777442f, 0.0240583373f, 0.0240583373f,
            0.0240583373f, 0.0237437801f, 0.0235213863f, 0.0235213863f, 0.0235086588f, 0.0235086588f, 0.0231670371f,
            0.0231168762f, 0.0231168762f, 0.0231516124f, 0.0231516124f, 0.0230725495f, 0.0230191701f, 0.022869021f,
            0.0227657504f, 0.0227278374f, 0.0227278374f, 0.0227278374f, 0.0222939681f, 0.0222939681f, 0.0222939681f,
            0.022398752f, 0.022398752f, 0.0222650649f, 0.0220789641f, 0.0220789641f, 0.0220789641f, 0.0221231578f, 0.0220116148f,
            0.0219830548f, 0.0219830548f, 0.0216861555f, 0.0216561792f, 0.021636341f, 0.0213107948f, 0.0213107948f, 0.021197159f,
            0.0211816036f, 0.0211816036f, 0.0209652435f, 0.0209652435f, 0.0209652435f, 0.0209652435f, 0.0209316337f,
            0.0209759185f, 0.0209759185f, 0.0209117659f, 0.0209117659f, 0.0209117659f, 0.0207419853f, 0.0207419853f,
            0.0207419853f, 0.0207419853f, 0.0207419853f, 0.0207153708f, 0.0205864141f, 0.0205322005f, 0.0205322005f,
            0.0205322005f, 0.0205322005f, 0.0205322005f, 0.0205693553f, 0.0205693553f, 0.0205991398f, 0.0205991398f,
            0.0206513321f, 0.0206513321f, 0.0206513321f, 0.0207577043f, 0.0211465302f, 0.0212458523f, 0.0212897005f,
            0.0213049224f, 0.0213066036f, 0.0213066036f, 0.0213066036f, 0.0212535691f, 0.0212535691f, 0.0212266862f,
            0.0211456235f, 0.0211456235f, 0.0210362955f, 0.0210362955f, 0.0210362955f, 0.0210756321f, 0.0212594082f,
            0.0213230132f, 0.0213230132f, 0.0213230132f, 0.0213870756f, 0.021466621f, 0.0215220443f, 0.0215701174f,
            0.0219568127f, 0.0219568127f, 0.0219568127f, 0.0218982842f, 0.0218982842f, 0.0218982842f, 0.0219357073f,
            0.0221278352f, 0.0222297462f, 0.0222297462f, 0.0222468899f, 0.0222468899f, 0.022300049f, 0.022300049f, 0.0224884174f,
            0.0224884174f, 0.0224902823f, 0.022563576f, 0.0228325229f, 0.0230046637f, 0.0230046637f, 0.0230046637f,
            0.0230046637f, 0.0230071901f, 0.0230071901f, 0.0230071901f, 0.0230071901f, 0.0228383006f, 0.0228383006f,
            0.0228383006f, 0.0228383006f, 0.0228097843f, 0.0223505387f, 0.0223505387f, 0.0223375297f, 0.0221755587f,
            0.0220124296f, 0.0220108014f, 0.0219728851f, 0.0218652928f, 0.0219254476f, 0.0219254476f, 0.0219254476f,
            0.0217079949f, 0.0216420613f, 0.0216363775f, 0.0216313082f, 0.0216027203f, 0.0214935775f, 0.0212059604f,
            0.0211947815f, 0.021093113f };

    float hbaMidFirst = 180.000000f; // freq of first element is: 180.000000 MHz
    float hbaMidLast = 217.187500f; // freq of last element is: 217.187500 MHz
    float hbaMidStep = .156250f; // frequency step = .156250 MHz
    float[] hbaMid = { 0.0088040107f, 0.0088928512f, 0.0088928512f, 0.0088928512f, 0.0087592269f, 0.0087230309f, 0.00860188f,
            0.0085660888f, 0.0085327823f, 0.0085327823f, 0.0084777554f, 0.0084777554f, 0.0084777554f, 0.0083548642f,
            0.0082984384f, 0.0082360326f, 0.0080671495f, 0.0080477481f, 0.0079870105f, 0.007916065f, 0.007916065f, 0.0077151261f,
            0.0076071313f, 0.0075941153f, 0.0075214258f, 0.00743223f, 0.0073355733f, 0.0073441505f, 0.0073198944f, 0.0071526045f,
            0.0071163708f, 0.0071072453f, 0.0070649299f, 0.0070285008f, 0.0067869603f, 0.0067487247f, 0.0067250053f,
            0.0067031319f, 0.0066061359f, 0.0065926669f, 0.0064246077f, 0.0064058197f, 0.0062828773f, 0.0062504938f,
            0.0062828773f, 0.0061701927f, 0.0062691927f, 0.0062502439f, 0.0060752746f, 0.0059697323f, 0.0058898471f,
            0.0058292735f, 0.0055476385f, 0.0055151376f, 0.0053654679f, 0.0051745527f, 0.0051003347f, 0.0050671716f,
            0.0048979351f, 0.0049065453f, 0.0050865179f, 0.006354983f, 0.0064338353f, 0.0064716892f, 0.0064338353f,
            0.0064106562f, 0.0064559632f, 0.006451669f, 0.006413435f, 0.006413435f, 0.0063884139f, 0.0063924954f, 0.0063985267f,
            0.0063985267f, 0.0063924954f, 0.0063132886f, 0.0062722336f, 0.0062367212f, 0.0062319757f, 0.0062291864f,
            0.0062291864f, 0.0061705524f, 0.0061539781f, 0.0061539781f, 0.0061539781f, 0.0060379588f, 0.0060201443f,
            0.0060201443f, 0.0060379588f, 0.0060104364f, 0.0060087551f, 0.0059824175f, 0.0059444163f, 0.0059324544f,
            0.0059324544f, 0.0058856596f, 0.0058448716f, 0.0058300613f, 0.0058300613f, 0.0058141899f, 0.0058141899f,
            0.0057243774f, 0.0056891616f, 0.0055796143f, 0.0055796143f, 0.0055796143f, 0.0055802574f, 0.0055741972f,
            0.0055741972f, 0.005576405f, 0.0055544413f, 0.0055136678f, 0.0054692017f, 0.0054536396f, 0.0054516106f,
            0.0054516106f, 0.0054389578f, 0.0053328414f, 0.0052973343f, 0.0052630089f, 0.005180207f, 0.0051666367f,
            0.0044337773f, 0.0044415961f, 0.0044112317f, 0.0043842676f, 0.0043842676f, 0.0043472729f, 0.0042821672f,
            0.0042821672f, 0.0042567262f, 0.0042291077f, 0.004189462f, 0.0042311708f, 0.0042260353f, 0.0041533718f,
            0.0041626082f, 0.0041521814f, 0.0041464403f, 0.0041464403f, 0.0041404591f, 0.0041119177f, 0.0041119177f,
            0.0040070612f, 0.0039989663f, 0.0039967603f, 0.0039670872f, 0.0039967603f, 0.0039670872f, 0.0039665973f,
            0.0039454859f, 0.0039330247f, 0.0039454859f, 0.0039427553f, 0.0039427553f, 0.0039506054f, 0.0039506054f,
            0.0039427553f, 0.0039112337f, 0.0039112337f, 0.0039112337f, 0.0039200004f, 0.0039200004f, 0.0039200004f,
            0.0039191855f, 0.0039175676f, 0.0038012425f, 0.0039258222f, 0.0039175676f, 0.0038199195f, 0.003803482f,
            0.0038199195f, 0.0038199195f, 0.003803482f, 0.00380738f, 0.00380738f, 0.0036996092f, 0.0037498115f, 0.0037886817f,
            0.0037886817f, 0.0037116119f, 0.0037470204f, 0.0038632857f, 0.0039237221f, 0.0038373932f, 0.003798976f, 0.003798976f,
            0.0037422628f, 0.0037206915f, 0.0037206915f, 0.0037028743f, 0.003755049f, 0.0036932694f, 0.0035797016f,
            0.0035016535f, 0.0035665281f, 0.0035146709f, 0.0034508222f, 0.0035002558f, 0.0034533116f, 0.0034533116f,
            0.0033320486f, 0.0033266545f, 0.0032821283f, 0.0031860394f, 0.0032878943f, 0.0031605466f, 0.0031266716f,
            0.0031266716f, 0.003152007f, 0.0030772947f, 0.0030939852f, 0.0032202497f, 0.0031274362f, 0.0031274362f,
            0.0031706605f, 0.003273517f, 0.0032820139f, 0.0031759353f, 0.0031756782f, 0.0031088576f, 0.003102291f, 0.0030421134f,
            0.0030388982f, 0.0029681306f, 0.0029036831f, 0.0029036831f, 0.0029220776f, 0.0028655052f, 0.0027296113f,
            0.0026719384f, 0.0026619021f, 0.0025804268f, 0.0025752324f, 0.0025752324f, 0.0025847796f, 0.0024316156f,
            0.0024316156f, 0.0024215716f };

    float hbaHighFirst = 210.156250f; // freq of first element is: 210.156250 MHz
    float hbaHighLast = 249.8046875f; // freq of last element is: 249.8046875 MHz
    float hbaHighStep = 0.1953125f; // frequency step = .1953125 MHz
    float[] hbaHigh = { 0.0137673336f, 0.0137673336f, 0.0145473524f, 0.0151546596f, 0.0153252334f, 0.0157781881f, 0.0165612279f,
            0.01720226f, 0.017825735f, 0.0188108385f, 0.019685912f, 0.0202019514f, 0.0210398799f, 0.0216139944f, 0.0222727912f,
            0.0225053977f, 0.0227057294f, 0.023276642f, 0.0235143862f, 0.0240658542f, 0.0244237282f, 0.024899808f, 0.0255407544f,
            0.0259151786f, 0.0261726187f, 0.0262956773f, 0.0265958477f, 0.0266842421f, 0.0269833107f, 0.0270264373f,
            0.0271776527f, 0.027716978f, 0.0278083667f, 0.0278610723f, 0.0278998968f, 0.0281772481f, 0.0283260238f,
            0.0287618093f, 0.0288252512f, 0.0288702769f, 0.0289340744f, 0.028979296f, 0.0290587496f, 0.0291774766f,
            0.0291774766f, 0.0291774766f, 0.0290977573f, 0.0290062677f, 0.0291287782f, 0.0291930094f, 0.0293762152f,
            0.0293762152f, 0.0294096552f, 0.0296086768f, 0.0296086768f, 0.0296086768f, 0.0296086768f, 0.0294338083f,
            0.0294338083f, 0.029491567f, 0.0295025104f, 0.0295025104f, 0.0295025104f, 0.0294628723f, 0.0294382031f,
            0.0294973007f, 0.029504084f, 0.029504084f, 0.029504084f, 0.029504084f, 0.029504084f, 0.029504084f, 0.029504084f,
            0.029504084f, 0.029504084f, 0.0294f, 0.0293f, 0.0292f, 0.029f, 0.0288f, 0.0286f, 0.0283f, 0.028f, 0.0278f, 0.0276f,
            0.0274f, 0.0272f, 0.0271f, 0.027f, 0.0269f, 0.0268f, 0.0266f, 0.0265f, 0.0264f, 0.02635f, 0.0262f, 0.0261f, 0.026f,
            0.0259f, 0.0258f, 0.0257f, 0.0256f, 0.0254f, 0.025230745f, 0.0249793555f, 0.0247735254f, 0.0246296815f,
            0.0245679441f, 0.0243498095f, 0.0241343842f, 0.0237717768f, 0.0236634705f, 0.0234905001f, 0.0231745691f,
            0.0229696971f, 0.0227194198f, 0.0227194198f, 0.0224354242f, 0.0222896865f, 0.0219331302f, 0.0216356263f,
            0.0214017081f, 0.0211360232f, 0.0210808089f, 0.0208021468f, 0.0206863078f, 0.020405003f, 0.020298032f, 0.020032706f,
            0.0197715252f, 0.0192503359f, 0.0194220358f, 0.019075875f, 0.0187695747f, 0.0182759879f, 0.0180825409f,
            0.0178428472f, 0.0178428472f, 0.0174192455f, 0.0172432111f, 0.0172432111f, 0.0168390734f, 0.0168100372f,
            0.0168100372f, 0.0167402396f, 0.016692012f, 0.0166524839f, 0.0163691726f, 0.0160097812f, 0.0158347566f,
            0.0157340857f, 0.0157451259f, 0.0155687281f, 0.0152855584f, 0.0154572936f, 0.0153839951f, 0.0151743156f,
            0.0150194376f, 0.0148847481f, 0.0147135568f, 0.0146675762f, 0.0145439123f, 0.0144438486f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f,
            0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f, 0.0144084868f };

    // TODO maybe it is quadratic, these are autocorrelations
    // TODO interpolate
    // TODO functions from freq to corrected value
    // TODO is scale between 0 and 1 correct? especially the 0?

    public AntennaBandpass() {
        DataProvider.scale(lba);
        DataProvider.scale(hbaLow);
        DataProvider.scale(hbaMid);
        DataProvider.scale(hbaHigh);
        /*
                System.err.println("START HBA LOW");
                for(int i=0; i<hbaLow.length; i++) {
                    System.err.println(hbaLow[i]);
                }
                System.err.println("END HBA LOW");
        */
    }

    public float getBandPassCorrectionFactor(AntennaType type, float frequency) {
        float[] freqs;
        float first;
        float last;
        float step;
        switch (type) {
        case LBA:
            freqs = lba;
            first = lbaFirst;
            last = lbaLast;
            step = lbaStep;
            break;
        case HBA_LOW:
            freqs = hbaLow;
            first = hbaLowFirst;
            last = hbaLowLast;
            step = hbaLowStep;
            break;
        case HBA_MID:
            freqs = hbaMid;
            first = hbaMidFirst;
            last = hbaMidLast;
            step = hbaMidStep;
            break;
        case HBA_HIGH:
            freqs = hbaHigh;
            first = hbaHighFirst;
            last = hbaHighLast;
            step = hbaHighStep;
            break;
        default:
            throw new RuntimeException("illegal antenna type");
        }

        if (frequency < first || frequency > last) {
            System.out.println("warning: illegal frequency " + frequency);
            return 0.0f;
        }

        // TODO interpolate
        int index = (int) ((frequency - first) / step);
        if (index >= freqs.length) {
            index = freqs.length - 1;
        }

        return 1.0f - freqs[index];
    }
}
