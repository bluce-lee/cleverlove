pragma solidity ^0.4.21;
/*
	v2.3
	开启游戏，注册两个玩家，分别输入谜底与题面，查看对方题面，
	输入自己的答案，双方揭示答案，游戏结束，赌金结算。
	若时间超过仍未进行游戏，则可以点击timeOutOver退还押金。
*/
contract wordGuessingGame{
    struct Player{
        address _address;			//玩家地址
        uint bet;					//赌金
        string challenge;			//单词题面
        uint16 challengeIndex1;      
    	uint16 challengeIndex2;
        bool ready;					//准备情况
        bool played;				//提交情况
        string commitWord;			//谜底单词
        string guessesWord;			//猜测对面单词
        bool validata;              //
    }
    
    Player[2] private players;		//玩家组限制2人对赌
    Player public theWinner;		//获胜者
    
    bool public gameFinished;       //游戏是否结束
    bool public gameReady;          //双方题面答案是否准备完成
    bool public player1Committed;   //玩家1是否提交题面答案
    bool public player2Committed;	//玩家2...
    bool public player1Revealed;    //玩家1是否揭示答案
    bool public player2Revealed;	//玩家2...
    bool public player1Cheater;		//玩家1是否答对
    bool public player2Cheater;		//玩家2...
    uint public EndTime;
    
    uint16 wordSize = 5;			//单词长度
    uint8 count = 0;				//当前玩家计数
    uint minimumBet;				//最小的赌金

    event GameStartsEvent(address player1, address player2); 
    event EndOfGameEvent(address winner, uint gains);
    event Tie(string);


     /*
        onlyRegisteredPlayers:只有当前的玩家可以调用的函数
    */
    modifier onlyRegisteredPlayers(){
        log0(64);
        require(msg.sender == players[0]._address || msg.sender == players[1]._address);
        log0(63);
        _;
    } 
    
     
    /*
        commitmentDone: 当玩家双方都提交谜底与题面时可以调用的函数
    */
    modifier commitmentDone(){
        log0(62);
        require(player1Committed == true && player2Committed == true);
        log0(61);
        _;
    }
    
	/*
		wordGuessingGame: 初始化游戏
	*/
    function wordGuessingGame(uint _minimumBet,uint16 _wordSize) public{ 
        minimumBet = _minimumBet;
        wordSize = _wordSize;
        EndTime = now + 120* 1 seconds;
    }
    
    /*
		register: 押上赌金并成为玩家
	*/
    function register() public payable{
        log0(0);
        require(count < 2);											//要求少于两人
        log0(1);
        require(players[count]._address == address(0));				//不能重复
        log0(2);
        require(msg.value >= minimumBet);							//赌金必须大于最小押金
        log0(3);
        if(count == 1){
            log0(4);
            require(msg.sender != players[0]._address);
            log0(5);
        }
        players[count] = Player(msg.sender,msg.value,"",wordSize,wordSize,false,false,"","",false);
        count++;
    }
    
	/*
		commitWord: 提交谜底
	*/
    function commitWord(string _commitWord) public onlyRegisteredPlayers{
        log0(6);
        require(bytes(_commitWord).length == wordSize);
        log0(7);
        if(msg.sender == players[0]._address){
            log0(8);
            players[0].commitWord = _commitWord;
            player1Committed = true;
        }else{
            log0(9);
            players[1].commitWord = _commitWord;
            player2Committed = true;
        }
    }
    
	
	/*
		submitChallenge: 提交题面
	*/
    function submitChallenge(string challenge, uint16 i, uint16 j) public onlyRegisteredPlayers commitmentDone{
        log0(10);
        require(!gameReady && (msg.sender == players[0]._address || msg.sender == players[1]._address));
        log0(11);
        require(bytes(challenge).length == wordSize);
        log0(12);
        if(msg.sender == players[0]._address) {
            log0(13);
            require(players[0].ready == false);
            log0(14);
            // require(validata() == true);
            players[0].challenge = challenge;
            players[0].challengeIndex1 = i;
            players[0].challengeIndex2 = j;
            players[0].ready = true;
        }else{
            log0(15);
            require(players[1].ready == false);
            log0(16);
            // require(validata() == true);
            players[1].challenge = challenge;
            players[1].challengeIndex1 = i; 
            players[1].challengeIndex2 = j;
            players[1].ready = true;
        }
        
        log0(17);
        if(players[0].ready && players[1].ready){
            gameReady = true; 
            emit GameStartsEvent(players[0]._address,players[1]._address);
        } 
    }
    
    /*
        validata:验证谜底与题面是否符合规则
    */
    function validata()public{
        uint k;
        log0(18);
        if (msg.sender == players[0]._address){
            k = 0;
        }else{
            log0(19);
            k = 1;
        }
        log0(20);
        for(uint i=0;i<wordSize;i++){
            log0(21);
            if(i!=players[k].challengeIndex1&&i!=players[k].challengeIndex2){
                log0(22);
                require(bytes(players[k].challenge)[i]== bytes(players[k].commitWord)[i]);
                log0(23);
            }
        }
        players[k].validata = true;
    }
    
    
    
	/*
		play: 提交自己对对方题目的答案
	*/
    function play(string _guessesWord) public onlyRegisteredPlayers commitmentDone{
        log0(24);
        require(gameReady&&!gameFinished);
        log0(25);
        if(msg.sender == players[0]._address){
            log0(26);
            require(players[0].played == false);
            log0(27);
            players[0].guessesWord = _guessesWord;
            players[0].played = true;
        } else{
            log0(28);
            require(players[1].played == false);
            log0(29);
            players[1].guessesWord = _guessesWord;
            players[1].played = true;
        }
    }
    
	/*
		reveal: 揭示自己是否答对,当双方都确认自己正确与否之后，游戏结束进行结算
	*/
    function reveal() public onlyRegisteredPlayers commitmentDone{
        log0(30);
        require(gameReady && !gameFinished && players[0].played&& players[1].played);
        log0(31);
        if (msg.sender == players[0]._address && !player1Revealed){
            player1Revealed = true;
            log0(32);
            if(keccak256(players[0].guessesWord) == keccak256(players[1].commitWord)){
                player1Cheater = true;
            }
            
        }else if(msg.sender == players[1]._address && !player2Revealed){
            log0(33);
            player2Revealed = true;
            log0(34);
            if(keccak256(players[1].guessesWord) == keccak256(players[0].commitWord)){
                player2Cheater = true;
            }
        }
        log0(35);
        if(player1Revealed && player2Revealed){
            endOfGame();
        }
    }
    
	/*
		prompt: 查看对方题面
	*/
    function prompt() public onlyRegisteredPlayers commitmentDone returns (string _challenge){
        log0(36);
        require(gameReady);
        log0(37);
        if (msg.sender == players[0]._address){
            _challenge = players[1].challenge;
        }else if(msg.sender == players[1]._address){
            log0(38);
            _challenge = players[0].challenge;
        }
    }
    
    function timeOutOver() public {
        log0(39);
        require(now > EndTime);
        log0(40);
		require(gameFinished!=true);
		log0(41);
        gameFinished = true;
        emit Tie("Time out! GameOver");
        players[0]._address.transfer(players[0].bet);
        players[1]._address.transfer(players[1].bet);
    }


	/*
		endOfGame: 结算赌金
	*/
    function endOfGame() internal{
        gameFinished = true; 
        log0(42);
        require(players[0].validata&&players[1].validata);
        log0(43);
        if((!player1Cheater && !player2Cheater)||(player1Cheater && player2Cheater)) {
            emit Tie("There is a tie! Both palyers will receive their bets back!");
            players[0]._address.transfer(players[0].bet);
            players[1]._address.transfer(players[1].bet);
        }else if(!player1Cheater && player2Cheater){
            log0(44);
            theWinner = players[1];
            players[1]._address.transfer(players[0].bet);
            players[1]._address.transfer(players[1].bet);
        }else if(player1Cheater && !player2Cheater){  
            log0(45);
            theWinner = players[0];
            players[0]._address.transfer(players[1].bet);
            players[0]._address.transfer(players[0].bet);
        }
     }
    
}