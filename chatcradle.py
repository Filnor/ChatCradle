import time
from chatoverflow.chatexchange.client import Client

def main():
    room_queue = [ #Add a comma-separated list of all rooms, in which the bot should join, here
        167908, #SOBotics Workshop
        163468 #Filnor's HQ
    ]
    rooms = []

    print("Logging in...")
    #Login and connection to chat
    client = Client("stackoverflow.com")
    client.login("<email>", "<password>")

    #Get object instances for the room(s)
    for room_to_join in room_queue:
        rooms.append(client.get_room(room_to_join))

    for room in rooms:
        room.join()

    print("Joined room(s).")

    while True:
        for room in rooms:
            room.send_message("[ [ChatCradle](https://git.io/fxa52) ] Keeping the room fresh...")
            print(f"Sent keep-alive message to room {room.name} (id {room.id})")
        time.sleep(86400)

if __name__ == '__main__':
    main()