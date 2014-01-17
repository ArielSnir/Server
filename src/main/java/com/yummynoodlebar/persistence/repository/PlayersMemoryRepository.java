package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.Player;

import java.util.*;

public class PlayersMemoryRepository implements PlayersRepository {

	  private Map<UUID, Player> players;

	  public PlayersMemoryRepository(final Map<UUID, Player> players) {
	    this.players = Collections.unmodifiableMap(players);
	  }

  
  @Override
  public synchronized Player save(Player player) {

    Map<UUID, Player> modifiablePlayers = new HashMap<UUID, Player>(players);
    modifiablePlayers.put(player.getKey(), player);
    this.players = Collections.unmodifiableMap(modifiablePlayers);

    return player;
  }

/*  @Override
  public synchronized void delete(UUID key) {
    if (players.containsKey(key)) {
      Map<UUID, Player> modifiablePlayers = new HashMap<UUID, Player>(players);
      modifiablePlayers.remove(key);
      this.players = Collections.unmodifiableMap(modifiablePlayers);
    }
  }

  @Override
  public Player findById(UUID key) {
    return players.get(key);
  }*/

  @Override
  public List<Player> findAll() {
    return Collections.unmodifiableList(new ArrayList<Player>(players.values()));
  }


@Override
public long count() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public void delete(String arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void delete(Player arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void delete(Iterable<? extends Player> arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void deleteAll() {
	// TODO Auto-generated method stub
	
}


@Override
public boolean exists(String arg0) {
	// TODO Auto-generated method stub
	return false;
}


@Override
public Iterable<Player> findAll(Iterable<String> arg0) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Player findOne(String arg0) {
	// TODO Auto-generated method stub
	return null;
}

/*
@Override
public <S extends Player> S save(S arg0) {
	// TODO Auto-generated method stub
	return null;
}
*/

@Override
public <S extends Player> Iterable<S> save(Iterable<S> arg0) {
	// TODO Auto-generated method stub
	return null;
}

}
