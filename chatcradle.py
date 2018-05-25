import time
from chatexchange.chatexchange.client import Client

def main():

    print("Logging in...")
    #Login and connection to chat
    client = Client("stackoverflow.com")
    client.login("<email>", "<password>")
    room = client.get_room(<room number>)
    room.join()
    print("Joined room.")

    while True:
        room.send_message("[ [ChatCradle](https://stackoverflow.com/users/4733879/filnor) ] Keeping the room fresh...")
        print("Sent message")
        time.sleep(86400)

if __name__ == '__main__':
    main()